<?php
/**
 * AGB api. Realized with slim framework.
 * @author rqd3-u
 *
 */
header('Content-type: application/json;; charset=utf-8');

include '../AGBDBConnector.class.php';

$agbDBConnector = new AGBDBConnector();

/**
 * Step 1: Require the Slim Framework
 *
 * If you are not using Composer, you need to require the
 * Slim Framework and register its PSR-0 autoloader.
 *
 * If you are using Composer, you can skip this step.
 */
require 'Slim/Slim.php';

\Slim\Slim::registerAutoloader();

/**
 * Step 2: Instantiate a Slim application
 *
 * This example instantiates a Slim application using
 * its default settings. However, you will usually configure
 * your Slim application now by passing an associative array
 * of setting names and values into the application constructor.
 */
$app = new \Slim\Slim();
$app->contentType('text/html; charset=utf-8');

/*
// route middleware for simple API authentication
function authenticate(\Slim\Route $route) {
    $app = \Slim\Slim::getInstance();
    $uid = $app->getEncryptedCookie('uid');
    $key = $app->getEncryptedCookie('key');
    if (validateUserKey($uid, $key) === false) {
      $app->halt(401);
    }
}

function validateUserKey($uid, $key) {
  // insert your (hopefully more complex) validation routine here
  if ($uid == 'demo' && $key == 'demo') {
    return true;
  } else {
    return false;
  }
}

// generates a temporary API key using cookies
// call this first to gain access to protected API methods
$app->get('/demo', function () use ($app) {    
  try {
    $app->setEncryptedCookie('uid', 'demo', '5 minutes');
    $app->setEncryptedCookie('key', 'demo', '5 minutes');
  } catch (Exception $e) {
    $app->response()->status(400);
    $app->response()->header('X-Status-Reason', $e->getMessage());
  }
});*/

$app->notFound(function () use ($app) {
    echo '{
"error": "404",
"error_description": "Ressource not found. Check your url."
}';
});

/**
 * Step 3: Define the Slim application routes
 *
 * Here we define several Slim application routes that respond
 * to appropriate HTTP request methods. In this example, the second
 * argument for `Slim::get`, `Slim::post`, `Slim::put`, `Slim::patch`, and `Slim::delete`
 * is an anonymous function.
 */


//get all agbsources
$app->get('/api1.0/agbsources/', function () {
	$agbSources = $GLOBALS['agbDBConnector']->getAllAGBSources();

    echo json_encode($agbSources,  JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
	
});

//get single agbsource by agbsourceid
$app->get('/api1.0/agbsources/:agbsourceid', function ($agbSourceId) {
	$agbSources = $GLOBALS['agbDBConnector']->getAGBSource($agbSourceId);

    echo json_encode($agbSources,  JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
});

//get latest agbversion
$app->get('/api1.0/agbversions/latest/:agbsourceid', function ($agbSourceId) {
	$latestAgbVersion = $GLOBALS['agbDBConnector']->getLatestVersionOfDB($agbSourceId);
    echo json_encode($latestAgbVersion,  JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);

});

//get all versions of agbSource sorted by date. latest first.
$app->get('/api1.0/agbversions/:agbsourceid', function ($agbSourceId) {
	$versionsOfAGBSource = $GLOBALS['agbDBConnector']->getVersionsOfAGBSource($agbSourceId);
    echo json_encode($versionsOfAGBSource,  JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
});

//get top ten agbsources  
$app->get('/api1.0/agbsources/topten/', function () {
	$agbSources = $GLOBALS['agbDBConnector']->getTopTenAGBSources();
    echo json_encode($agbSources, JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
});

//@TODO AGBSource(sourceName), agbVersion(agbSourceId, version)

/**
 * Step 4: Run the Slim application
 *
 * This method should be called last. This executes the Slim application
 * and returns the HTTP response to the HTTP client.
 */
$app->run();
