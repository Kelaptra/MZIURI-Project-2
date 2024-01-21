var serverPath = "http://localhost:8989/messenger";
var userServletPath = "/user";
var messageServletPath = "/message";

async function send(){
    var param1 = document.getElementById("username").value;
    var param2 = document.getElementById("message").value;
    var url = serverPath + userServletPath + `?username=${param1}&message=${param2}`;
    var response = await fetch(url, { method: "POST" });
    if(response.ok){
        window.alert("Congrats! You registered successfully.");
    }
    else if(response.status == 403){
        window.alert("Couldn't send message:\nThis username doesn't exist or message contains multiple lines.");
    }
    else{
        window.alert("Something went wrong :(");
    }
}

async function getMessages(){
    var param1 = document.getElementById("myUsername").value;
    var param2 = document.getElementById("password").value;
    var url = serverPath + userServletPath + `?username=${param1}` + ``&password=${param2}``;
    var response = await fetch(url, { method: "GET" });
    if(response.ok){
        alert("Inbox checking is successful");
    }
    else if(response.status == 403){
        alert("Couldn't check Inbox: Invalid input");
    }
    else{
        alert("Something went wrong :(");
    }
    var body = await response.text();
    var div = document.getElementById("myMessageList");
    var messages = body.split("\n");
    var messageList = "";
    for(var message of messages){
        messageList += `<ul>${message}</ul>\n`
    }
    div.innerHTML = messageList;
}

async function register() {
    var param1 = document.getElementById("new-username").value;
    var param2 = document.getElementById("new-password").value;
    var url = serverPath + userServletPath + `?username=${param1}` + ``&password=${param2}``;
    var response = await fetch(url, { method: "POST" });
    if(response.ok){
        alert("Congrats! You registered successfully.");
    }
    else if(response.status == 403){
        alert("This Username already exists");
    }
    else{
        alert("Something went wrong :(");
    }
}

function clearElementById(var id){
    document.getElementById(id).value = "";
}

function getParam(var id){
    return document.getElementById(id).value;
}
