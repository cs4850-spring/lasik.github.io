var outputWindow = document.getElementById("Translate_Code");
var inputWindow = document.getElementById("Java_Code");

const generationURL = "http://api.michaelepps.me:8080/parse";

var generateCode = () => 
{
	var input_code = inputWindow.value;
	fetch(generationURL, 
	{
		method: "POST", 
		body: input_code
	}).then((response) => 
	{
		outputWindow.value = response;
		alert("Translation has been completed");
	}).catch((error) =>
	{
		outputWindow.value = error;
		alert("There was an error in translation");
	})
}