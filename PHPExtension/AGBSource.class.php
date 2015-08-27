<?php
class AGBSource
{
    public $agbSourceId;
    public $name;
    public $link;
    public $season;
	public $xPath;
	
    function AGBSource($agbSourceId, $name, $link, $season, $xPath)
    {
        $this->agbSourceId  = $agbSourceId;
        $this->name         = $name;
        $this->link      = $link;
        $this->season  = $season;
		$this->xPath = $xPath;
    }

    public function getAgbSourceId()
    {
        return $this->agbSourceId;
    }
    public function setAgbSourceId($agbSourceId)
    {
        $this->agbSourceId = $agbSourceId;
    }
    public function getName()
    {
        return $this->name;
    }
    public function setName($name)
    {
        $this->name = $name;
    }
    public function getLink()
    {
        return $this->link;
    }
    public function setLink($link)
    {
        $this->link = $link;
    }
    public function getSeason()
    {
        return $this->season;
    }
    public function setSeason($season)
    {
        $this->season = $season;
    }
	public function getXPath()
    {
        return $this->xPath;
    }
    public function setXPath($xPath)
    {
        $this->xPath = $xPath;
    }
	
	public function toString()
    {
        return "kljsdf";
    }
}
?>