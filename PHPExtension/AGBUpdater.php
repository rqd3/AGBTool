<?php
/**
 * Updates db from sources(also db)
 * @author rqd3-u
 *
 */
echo '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">';
header('Content-type: text/html; charset=utf-8');

include 'AGBDBConnector.class.php';

$agbDBConnector = null;

main();

	/*
	Start function
	*/
	function main(){
		set_time_limit(120000);
		$GLOBALS['agbDBConnector'] = new AGBDBConnector();
		
		if($GLOBALS['agbDBConnector'] != false){
			updateAgbs();
		}else{
			echo 'Cant access db';
		}
		
	}
	
	/*
	Compare online and latest db version and check for possible states(like does version already exists or not). Update version if necessary
	*/
	function updateAgbs(){	

		$agbSources = $GLOBALS['agbDBConnector']->getAllAGBSources();
		
		$max = sizeof($agbSources);
		for($i=0; $i<$max; $i++)
		{
		   $agbSourceId = $agbSources[$i]->getAgbSourceId();

		   $latestVersionDB = $GLOBALS['agbDBConnector']->getLatestVersionOfDB($agbSourceId);
		   $latestVersionTextOnline = getLatestVersionOnline($agbSources[$i]->getLink(), $agbSources[$i]->getXPath());

			
			echo "source ".   $agbSourceId.": ";
			

			if($latestVersionTextOnline==null){
			  echo $agbSources[$i]->getLink();
			  echo ' online version not available. Check xpath and if user agents in fileGetContentUtf8($url) are up to date';
			}
	
		   //entry for this source available exists already
		   if($latestVersionDB!=null && $latestVersionTextOnline!=null){ 
			$latestVersionTextDB =  $latestVersionDB->getText();
			//strcmp =0 if string equals, if online text is !=null(and db text)
			if(strcasecmp($latestVersionTextDB,$latestVersionTextOnline )==0 ){
				echo "agb is up to date";
			}
			if(strcasecmp($latestVersionTextDB,$latestVersionTextOnline )!=0 ){
			   //update version
			    echo 'version updating';
				$GLOBALS['agbDBConnector']->updateAGBToDB($agbSourceId, $latestVersionTextOnline);			
			}
		   }
		   
		   //first version entry
		   if($latestVersionDB==null){
			   echo 'version created';
			   $GLOBALS['agbDBConnector']->addAGBToDB($agbSourceId, $latestVersionTextOnline);
			   $GLOBALS['agbDBConnector']->addAGBFavorite($agbSourceId);
		   }
		   
		   echo "</br></br>";
		}
	}

	/* Load a agb text from online source by url and by xPath 
	*  @param $url = path to agb
	*  @param $xPath = xPath
	*  @Return String agbText
	*/
	function getLatestVersionOnline($url, $xPath){
		//get newest agb as html from website
		$agbTextOnline = fileGetContentUtf8($url); 

		$doc = new DOMDocument();
		@$doc->loadHTML($agbTextOnline); //@is important
	 
	 
		$domXPath = new DOMXPath($doc);
		$agbText = $domXPath->evaluate('string('.$xPath.')'); //string(/html/head/title)'
		
	 	//trim delete spaces beginning end
		$agbText = trim($agbText);
		
		$agbTextWithoutControlChar = str_replace(array("\r", "\n", "\t"), ' ', $agbText);

		return $agbTextWithoutControlChar;
	}
	
	/**Request content in utf8 from url
	*  @param $url
	*/
	function fileGetContentUtf8($url) {
		$opts = array(
			'http' => array(
				'method'=>"GET",
				'header'=>	"Content-Type: text/html; charset=UTF-8\r\n" .
							"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36\r\n" //sites like facebook only give access for latest browsers
			)
		);

		$context = stream_context_create($opts);
		$result = @file_get_contents($url,false,$context);
		return $result;
	} 

?>