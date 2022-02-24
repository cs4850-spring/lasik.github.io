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

function saveAs_Translate() {
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

function handleFileSelecting(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

        var reader = new FileReader();

        // Closure to capture the file information.
        reader.onload = (function(theFile) {
            return function(e) {

                var content = e.target.result;
                if (content) {
                    // Inserting content into textarea, change id if you need
                    var textarea = document.getElementById('Java_Code');
                    textarea.innerHTML = content;

                }
            };
        })(f);

        reader.readAsText(f);
    }
}

function Load() {
    var filesElem = document.getElementById('files');
    filesElem.addEventListener('change', handleFileSelecting, false);
    filesElem.click();
}
