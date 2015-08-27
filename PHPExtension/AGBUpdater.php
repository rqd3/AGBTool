<?php
echo '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">';
header('Content-type: text/html; charset=utf-8');

include 'AGBDBConnector.class.php';

$agbDBConnector = null;

main();

	/*
	Start function
	*/
	function main(){
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
		   
		   //first version entry
		   if($latestVersionDB==null){
			   echo 'version created';
			   $GLOBALS['agbDBConnector']->addAGBToDB($agbSourceId, $latestVersionTextOnline);
		   }
		   //entry for this source available exists already
		   if($latestVersionDB!=null){ 
			$latestVersionTextDB =  $latestVersionDB->getText();		

			if($latestVersionTextOnline==null){
			  echo $agbSources[$i]->getLink();
			  echo ' online version not available ';
			}
			//strcmp =0 if string equal
			if(strcasecmp($latestVersionTextDB,$latestVersionTextOnline )==0){
				echo "agb is up to date";echo $latestVersionTextOnline;
			}
			else{
			   //update version
				$GLOBALS['agbDBConnector']->updateAGBToDB($agbSourceId, $latestVersionTextOnline);
			}
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
		$agbTextOnline = fileGetContentsUtf8($url); 

		$doc = new DOMDocument();
		@$doc->loadHTML($agbTextOnline);
	 
		$domXPath = new DOMXPath($doc);
		$agbText = $domXPath->evaluate('string('.$xPath.')'); //string(/html/head/title)'

	 	//trim delete spaces beginning end
		$agbText = trim($agbText);

		return $agbText;
	}
	
	/**Request content in utf8 from url
	*  @param $url
	*/
	function fileGetContentsUtf8($url) {
		$opts = array(
			'http' => array(
				'method'=>"GET",
				'header'=>"Content-Type: text/html; charset=UTF-8"
			)
		);

		$context = stream_context_create($opts);
		$result = @file_get_contents($url,false,$context);
		return $result;
	} 

?>