<?php
include 'AGBVersion.class.php';
include 'AGBSource.class.php';

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
		$abfrage = "SELECT * FROM `agb_version` WHERE published_at = (SELECT MAX(published_at) FROM agb_version WHERE `agb_source_id` like ".$agbSourceId.") AND `agb_source_id` like ".$agbSourceId."";
		$ergebnis = mysqli_query($this->mysqli, $abfrage); 

		return $this->sqlToAGBVersionObject($ergebnis);
	}
	
	/* Get all versions of an agb source
	*  @param $agbSourceId
	*  @Return AGBVersion[] $versionsOfAGBSource
	*/
	function getVersionsOfAGBSource($agbSourceId){
		//Select row with max published_at value
		$abfrage = "SELECT * FROM `agb_version` WHERE `agb_source_id` LIKE ".$agbSourceId."";
		$ergebnis = mysqli_query($this->mysqli, $abfrage);
		
		return $this->sqlToAGBVersionObject($ergebnis);
	}
	
		/**
	* add agb version
	* @param $agbSourceId
	* @param $latestVersionTextOnline
	*/
	function addAGBToDB($agbSourceId, $latestVersionTextOnline){
		//create version
		$addAgbToDB = "INSERT INTO `agb_tool_db`.`agb_version` (`agb_version_id`, `agb_source_id`, `text`, `version`, `published_at`) 
							VALUES (NULL, '".$agbSourceId."', '". mysqli_real_escape_string($this->mysqli, $latestVersionTextOnline)."' ,'1', '".date('Y-m-d G:i:s')."')"; //mysql_real_escape_string -> don't use commands in string for mysql
		mysqli_query($this->mysqli, $addAgbToDB);
	}
	
	/**
	* update agb version
	* @param $agbSourceId
	* @param $latestVersionTextOnline
	*/
	function updateAGBToDB($agbSourceId, $latestVersionTextOnline){
		$versionCounter = $this->getLatestVersionOfDB($agbSourceId)->getVersion();
		$versionCounter = $versionCounter+1;
		echo $versionCounter;
		
		$addAgbToDB = "INSERT INTO `agb_tool_db`.`agb_version` (`agb_version_id`, `agb_source_id`, `text`, `version`, `published_at`) 
							VALUES (NULL, '".$agbSourceId."', '".mysqli_real_escape_string($this->mysqli, $latestVersionTextOnline)."', '". $versionCounter ."', '".date('Y-m-d G:i:s')."')";
		mysqli_query($this->mysqli, $addAgbToDB);
		echo 'version is now up to date';
	}
	
	/**
	* Get single agbsource entry from db
	* @return List<AGBSource> agbSource there should be only one itme in list
	*/
	function getAGBSource($agbSourceId){
		$abfrage = "SELECT * FROM agb_source WHERE agb_source_id LIKE ".$agbSourceId;
		$ergebnis = mysqli_query($this->mysqli, $abfrage);
		
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
	 * Returns all agbSources which are on toptenlist(agb_favorite in db)
	 * 
	 * @return List<AGBSource> topTenAGBSources
	 */
	function getTopTenAGBSources(){
		$abfrage = "SELECT agb_source.agb_source_id, agb_source.name, agb_source.link, agb_source.season, agb_source.xpath, agb_favorite.agb_source_id 
				FROM agb_source 
				LEFT JOIN agb_favorite ON agb_source.agb_source_id=agb_favorite.agb_source_id 
				Where agb_favorite.agb_favorite_id IS NOT NULL";
				
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
		   $season = $row->season; 		   
		   $xPath =  $row->xpath;
		   $agbSources[] = new AGBSource($agbSourceId, $name, $link, $season, $xPath);
		}
		return $agbSources;
	}
	
	/**
	* Transforms sql result into agbversion object
	* @param $ergebnis = sql response
	* @return List<AGBVersion>
	*/
	function sqlToAGBVersionObject($ergebnis){
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