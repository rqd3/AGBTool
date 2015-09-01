<?php
class AGBVersion
{
    public $agbVersionId=1;
    public $agbSourceId;
    public $text;
    public $version;
    public $publishedAt;
    function AGBVersion($agbVersionId, $agbSourceId, $text, $version, $publishedAt)
    {
        $this->agbVersionId = $agbVersionId;
        $this->agbSourceId  = $agbSourceId;
        $this->text         = $text;
        $this->version      = $version;
        $this->publishedAt  = $publishedAt;
    }
    public function getAgbVersionId()
    {
        return $this->agbVersionId;
    }
    public function setAgbVersionId($agbVersionId)
    {
        $this->agbVersionId = $agbVersionId;
    }
    public function getAgbSourceId()
    {
        return $this->agbSourceId;
    }
    public function setAgbSourceId($agbSourceId)
    {
        $this->agbSourceId = $agbSourceId;
    }
    public function getText()
    {
        return $this->text;
    }
    public function setText($text)
    {
        $this->text = $text;
    }
    public function getVersion()
    {
        return $this->version;
    }
    public function setVersion($version)
    {
        $this->version = $version;
    }
    public function getPublishedAt()
    {
        return $this->publishedAt;
    }
    public function setPublishedAt($publishedAt)
    {
        $this->publishedAt = $publishedAt;
    }
	
	public function toString()
    {
        return "kljsdf";
    }
}
?>