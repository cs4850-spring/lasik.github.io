var outputWindow = document.getElementById("Translate_Code");
var inputWindow = document.getElementById("Java_Code");

const generationURL = "http://api.lasik.michaelepps.me:8080/parse";

function showSnackbar() {
  var x = document.getElementById("snackbar");
  x.className = "show";
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

var generateCode = () => {	  
	var input_code = inputWindow.value;
	fetch(generationURL, {
		method: 'POST', 
		body: input_code
	}).then((response) => {
		return response.json();		
	}).then((json) => {
		outputWindow.value = JSON.stringify(json, null, 2);
		alert("Translation has been completed");
	}).catch((error) => {
		outputWindow.value = error;
		alert("There was an error in translation");
	})
}

function saveFileAs() {
	if (promptFilename = prompt("Save file as", "")) {
		var textBlob = new Blob([document.getElementById("Translate_Code").value], {type:'text/plain'});
		var downloadLink = document.createElement("a");
		downloadLink.download = promptFilename;
		downloadLink.innerHTML = "Download File";
		downloadLink.href = window.URL.createObjectURL(textBlob);
		downloadLink.click();
    delete downloadLink;
    delete textBlob;
	}
}

