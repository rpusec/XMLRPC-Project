 <?php
	class Beer
	{
		private $name;
		private $price;
		
		public function __construct($name, $price)
		{
			$this->name = $name;
			$this->price = $price;
		}
		
		public function setName($name)
		{
			$this->name = $name;
		}
		
		public function setPrice($price)
		{
			$this->price = $price;
		}
		
		public function getName()
		{
			return $this->name;
		}
		
		public function getPrice()
		{
			return $this->price;
		}
		
		public function __toString()
		{
			return $this->name . ", $" . $this->price;
		}
	}
 ?>