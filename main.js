var inputText = document.getElementById("input-text");
var outputText = document.getElementById("output-text");
var inputFile = document.getElementById("input-file");

var inputEditor = CodeMirror.fromTextArea(inputText, {
    lineNumbers: true,
    mode: "text/x-java",
});

var outputEditor = CodeMirror.fromTextArea(outputText, {
    lineNumbers: true,
    mode: "text/x-csharp",
    readOnly: true,
});

inputEditor.setSize("100%", "100vh");
outputEditor.setSize("100%", "100vh")

const generationURL = "http://api.lasik.michaelepps.me:8081/parse";

var translateCode = () => {	  
	fetch(generationURL, {
		method: 'POST', 
		body: inputEditor.getValue(),
	}).then((response) => {
		return response.text();		
	}).then((data) => {
		outputEditor.setValue(data);
		alert("Translation has been completed");
	}).catch((error) => {
		outputEditor.setValue(data);
		alert("There was an error in translation");
	})
}

var loadInputFile = () => {
    inputFile.click();
}

inputFile.onchange = (e) => {
    var file = e.target.files[0];
    
    var reader = new FileReader();
    reader.readAsText(file,'UTF-8');
 
    reader.onload = readerEvent => {
       var content = readerEvent.target.result; 

       inputEditor.setValue(content);
    }

    // Reset the input
    e.target.value = '';
}

var clearInput = () => {
	inputEditor.setValue('');
	outputEditor.setValue('');
}

var saveOutput = () => {
    var text = outputEditor.getValue();
    saveTextAsFile(".cs", text);
}

var saveInput = () => {
    var text = inputEditor.getValue();
    saveTextAsFile(".java", text);
 }

var saveTextAsFile = (fileExtension, text) => {
    if (promptFilename = prompt(`Save file as *${fileExtension}`, "")) {
        text = text.replace(/\n/g, "\r\n"); // To retain the Line breaks.

        var blob = new Blob([text], { type: "text/plain"});
    
        var downloadLink = document.createElement("a");
        downloadLink.download = promptFilename + fileExtension;
        downloadLink.innerHTML = "Download File";
        downloadLink.href = window.URL.createObjectURL(blob);
        downloadLink.click();
    
        delete downloadLink
        delete textBlob
    }
 }

