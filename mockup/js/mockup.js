window.addEventListener('load', function() {
	var self = this;
	var mockup_container = document.getElementById('mockup-container');
	var img = document.getElementById('mockimg');

	var pantry = document.getElementById('pantry');
	var fooddata = document.getElementById('fooddata');
	var shoplist = document.getElementById('shoplist');
	var mealplan = document.getElementById('mealplan');
	var recipesearch = document.getElementById('recipesearch');
	var recipepage = document.getElementById('recipepage');
	var input = document.getElementById('input');

	// add event listener to span texts
	pantry.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "pantry.jpg";
	});

	fooddata.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "fooddata.jpg";
	});

	shoplist.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "shoplist.jpg";
	});

	mealplan.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "mealplan.jpg";
	});

	recipesearch.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "recipesearch.jpg";
	});

	recipepage.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "recipepage.jpg";
	});

	input.getElementsByTagName('span')[0].addEventListener('click',function(){
		img.src = "input.jpg";
	});
});