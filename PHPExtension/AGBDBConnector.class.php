<?php
include 'AGBVersion.class.php';
include 'AGBSource.class.php';

/**
 * Handles communication with db
 * @author rqd3-u
 *
 */
class AGBDBConnector
{
	private $mysqli =null;
	
	/*Connects with db
	*/
	function AGBDBConnector(){
		$this->mysqli = new mysqli("localhost", "root", "", "agb_tool_db");
		mysqli_set_charset($this->mysqli,'utf8');
		
		if ($this->mysqli->connect_errno) {
		echo "Failed to connect to MySQL: (" . $this->mysqli->connect_errno . ") " . $this->mysqli->connect_error;
		return false;
		}
		else{
			return $this->mysqli;
		}
	}
  
  	/* Get latest agb version of a distinct agb source
	*  @param $agbSourceId
	*  @Return AGBVersion $latestVersiontDB
	*/
	function getLatestVersionOfDB($agbSourceId){
		//Select row with max published_at value
		$abfrage = $this->mysqli->prepare("SELECT * FROM `agb_version` WHERE published_at = (SELECT MAX(published_at) FROM agb_version WHERE `agb_source_id` like ?) AND `agb_source_id` like ?");
		$abfrage->bind_param("ii", $agbSourceId, $agbSourceId);
		$abfrage->execute();
		
		$ergebnis = $abfrage->get_result();

		return $this->sqlToAGBVersionObject($ergebnis);
	}
	
	/* Get all versions of an agb source
	*  @param $agbSourceId
	*  @Return AGBVersion[] $versionsOfAGBSource
	*/
	function getVersionsOfAGBSource($agbSourceId){
		//Select row with max published_at value
		$abfrage = $this->mysqli->prepare("SELECT * FROM `agb_version` WHERE `agb_source_id` LIKE ? ORDER BY `published_at` DESC");
		$abfrage->bind_param("i", $agbSourceId);
		$abfrage->execute();
		
		$ergebnis = $abfrage->get_result();
		
		return $this->sqlToAGBVersionObjectArray($ergebnis);
	}
	
		/**
	* add agb version
	* @param $agbSourceId
	* @param $latestVersionTextOnline
	*/
	function addAGBToDB($agbSourceId, $latestVersionTextOnline){
		$date = date('Y-m-d G:i:s');
		//create version
		$abfrage =$this->mysqli->prepare( "INSERT INTO `agb_tool_db`.`agb_version` (`agb_version_id`, `agb_source_id`, `text`, `version`, `published_at`) 
							VALUES (NULL, ?, ? ,'1', ?)"); //mysql_real_escape_string -> don't use commands in string for mysql
		$abfrage->bind_param("iss", $agbSourceId, $latestVersionTextOnline, $date);
		$abfrage->execute();
	}
	
	/**
	* add agb_favorit entry if agb source is used first time
	* @param $agbSourceId
	*/
	function addAGBFavorite($agbSourceId){
		$date = date('Y-m-d G:i:s');
		//create version
		$abfrage =$this->mysqli->prepare( "INSERT INTO `agb_tool_db`.`agb_favorite` (`agb_favorite_id`, `agb_source_id`, `counter` ) 
							VALUES (NULL, ?, NULL)"); //mysql_real_escape_string -> don't use commands in string for mysql
		$abfrage->bind_param("i", $agbSourceId);
		$abfrage->execute();
	}
	
	/**
	* update agb version
	* @param $agbSourceId
	* @param $latestVersionTextOnline
	*/
	function updateAGBToDB($agbSourceId, $latestVersionTextOnline){
		$date = date('Y-m-d G:i:s');
		$versionCounter = $this->getLatestVersionOfDB($agbSourceId)->getVersion();
		$versionCounter = $versionCounter+1;
		
		$abfrage = $this->mysqli->prepare("INSERT INTO `agb_tool_db`.`agb_version` (`agb_version_id`, `agb_source_id`, `text`, `version`, `published_at`) 
							VALUES (NULL, ?, ?, ?, ?)");
		$abfrage->bind_param("isis", $agbSourceId, $latestVersionTextOnline, $versionCounter, $date);
		$abfrage->execute();
	}
	
	/**
	* Get single agbsource entry from db
	* @return List<AGBSource> agbSource there should be only one itme in list
	*/
	function getAGBSource($agbSourceId){
		$abfrage = $this->mysqli->prepare("SELECT * FROM agb_source WHERE agb_source_id LIKE ?");
		
		$abfrage->bind_param("i", $agbSourceId);
		$abfrage->execute();
		
		$ergebnis = $abfrage->get_result();
		
		//array klammern zuviel
		return $this->sqlToAGBSourceObject($ergebnis);
	}
	
	/**
	* Get all agbsource entries from db
	* @return List<AGBSource> allAGBSources
	*/
	function getAllAGBSources(){
		$abfrage = "SELECT * FROM agb_source";
		$ergebnis = mysqli_query($this->mysqli, $abfrage);

		return $this->sqlToAGBSourceObject($ergebnis);
	}
	
	/**
	 * Returns the ten agbSources with highest counter(agb_favorite in db)
	 * 
	 * @return List<AGBSource> topTenAGBSources
	 */
	function getTopTenAGBSources(){
		$abfrage = "SELECT agb_source.agb_source_id, agb_source.name, agb_source.link, agb_source.xpath, agb_favorite.agb_source_id 
				FROM agb_source 
				LEFT JOIN agb_favorite ON agb_source.agb_source_id=agb_favorite.agb_source_id 
				Where 1 ORDER BY `counter` DESC LIMIT 10";
				
		$ergebnis = mysqli_query($this->mysqli, $abfrage);

		return $this->sqlToAGBSourceObject($ergebnis);
	}
	
	/**
	* Transforms sql result into agbsource object
	* @param $ergebnis = sql response
	* @return List<AGBSource>
	*/
	function sqlToAGBSourceObject($ergebnis){
		$agbSources = array();
		while($row = mysqli_fetch_object($ergebnis))
		{
		   $agbSourceId = $row->agb_source_id;
		   $name = $row->name; 
		   $link = $row->link; 		   
		   $xPath =  $row->xpath;
		   $agbSources[] = new AGBSource($agbSourceId, $name, $link, $xPath);
		}
		return $agbSources;
	}
	
	/**
	* Transforms sql result into agbversion object
	* @param $ergebnis = sql response
	* @return AGBVersion
	*/
	function sqlToAGBVersionObject($ergebnis){
		$agbVersion = null;
		while($row = mysqli_fetch_object($ergebnis))
		{
			$agbVersionId = $row->agb_version_id;
			$agbSourceId =  $row->agb_source_id;
			$text =  $row->text; //preg_replace('/\s+/', '', $row->text); //delete whitespaces
			$version =  $row->version;
			$publishedAt =  $row->published_at;
			
			$agbVersion = new AGBVersion($agbVersionId, $agbSourceId, $text, $version, $publishedAt);
		}
		return $agbVersion;
	}
	
	/**
	* Transforms sql result into agbversion object
	* @param $ergebnis = sql response
	* @return List<AGBVersion>
	*/
	function sqlToAGBVersionObjectArray($ergebnis){
		$agbVersions = array();
		while($row = mysqli_fetch_object($ergebnis))
		{
			$agbVersionId = $row->agb_version_id;
			$agbSourceId =  $row->agb_source_id;
			$text =  $row->text; //preg_replace('/\s+/', '', $row->text); //delete whitespaces
			$version =  $row->version;
			$publishedAt =  $row->published_at;
			
			$agbVersions[] = new AGBVersion($agbVersionId, $agbSourceId, $text, $version, $publishedAt);
		}
		return $agbVersions;
	}
}
  
  ?>