<?php
/**
 * AGBSource model
 * @author rqd3-u
 *
 */
class AGBSource
{
    public $agbSourceId;
    public $name;
    public $link;
	public $xPath;
	
    function AGBSource($agbSourceId, $name, $link, $xPath)
    {
        $this->agbSourceId  = $agbSourceId;
        $this->name         = $name;
        $this->link      = $link;
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