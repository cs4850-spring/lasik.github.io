var outputWindow = document.getElementById("Translate_Code");
var inputWindow = document.getElementById("Java_Code");

const generationURL = "http://api.lasik.michaelepps.me:8081/parse";

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
		return response.arrayBuffer;		
	}).then((data) => {
		outputWindow.value = data.toString();
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

function save_JavaCode(){
    var text = document.getElementById("Java_Code").value;
    text = text.replace(/\n/g, "\r\n"); // To retain the Line breaks.
    var blob = new Blob([text], { type: "text/plain"});
    var anchor = document.createElement("a");
    anchor.download = "Untitled.Java";
    anchor.href = window.URL.createObjectURL(blob);
    anchor.target ="_blank";
    anchor.style.display = "none"; // just to be safe!
    document.body.appendChild(anchor);
    anchor.click();
    document.body.removeChild(anchor);
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

