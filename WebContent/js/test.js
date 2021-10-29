var test = (function() {

    var errorQuestion = {
            id: 0,
            step: "0",
            testo: "Spiacenti, si è vericato un errore inatteso.",
            titolo: "Attenzione",
            exits: null
        },
        model = {
            surveyResponse: {},
            answers: {},
            step: "1.1",
            actualQuestion: errorQuestion,
            exits: errorQuestion.exits
        };

    function loadSurvey() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if(this.readyState === 4 && this.status === 200) {
                model.surveyResponse = JSON.parse(this.responseText);
                goToStep(model.step);
            }
        };
        xhttp.open("GET", "test?surveyId=1", true);
        xhttp.send();
    }

    function renderHeader(){
        document.getElementById("title").innerHTML = model.surveyResponse.title;
        document.getElementById("subtitle").innerHTML = model.surveyResponse.description;
    }

    function goToStep(step){
        if(!model.surveyResponse.questions){
            return model.actualQuestion;
        }
        var found = model.surveyResponse.questions.find(q => q.step === step);
        if(found !== undefined){
            model.actualQuestion = found;
        }
        render();
    }

    function start(){
        loadSurvey();
    }

    function answer(answer){

        model.answers[model.step] = {"risposta": answer};

        var foundExit = model.actualQuestion.exits.find(e => e.response === answer);

        if(foundExit !== undefined){
            // risposta finale
            if(foundExit.exit !== 0 ){
                // todo goToExit(findStepByQuestionId(foundExit.exit));
            } else if (foundExit.exitQuestion !== null) {
                model.step = findStepByQuestionId(foundExit.exitQuestion);
            } else{
                // todo errore
            }
        }

        goToStep(model.step);
    }

    function onAnswerClick(ev){
        answer(ev.currentTarget.value);
    }

    function findStepByQuestionId(id){
        var found = model.surveyResponse.questions.find(q => q.id === id);
        if(found !== undefined){
            return found.step;
        }
        return "";
    }

    function render(){
        var content = document.getElementById("test-content"),
            questionTitle = document.createElement('p'),
            questionDescription = document.createElement('p'),
            openResponse = document.createElement('textarea'),
            avantiButton = document.createElement('button'),
            yesButton = document.createElement('button'),
            noButton = document.createElement('button'),
            otherButton = document.createElement('button');

        content.innerHTML = "";
        questionTitle.innerHTML = model.actualQuestion.titolo;
        questionDescription.innerHTML = model.actualQuestion.testo;
        avantiButton.onclick = onAnswerClick;
        avantiButton.innerText = "PROSEGUI";
        noButton.onclick = onAnswerClick;
        noButton.innerText = "NO";
        noButton.value = 'n';
        otherButton.onclick = onAnswerClick;
        otherButton.innerText = "NON SO";
        otherButton.value = '0';
        yesButton.onclick = onAnswerClick;
        yesButton.innerText = "SI";
        yesButton.value = 'y';

        content.append(questionTitle);
        content.append(questionDescription);

        var showOpenResponse = model.actualQuestion.type === "00",
            showAvantiButton = model.actualQuestion.type === "00",
            showNoButton = model.actualQuestion.type !== "00",
            showNSButton = model.actualQuestion.type === "11",
            showSiButton = model.actualQuestion.type !== "00";

        if(showOpenResponse) {
            content.append(openResponse);
        }
        if(showAvantiButton) {
            content.append(avantiButton)
        }
        if(showNoButton) {
            content.append(noButton);
        }
        if(showNSButton) {
            content.append(otherButton);
        }
        if(showSiButton) {
            content.append(yesButton);
        }
    }

    return {
        start: start
    }
})();