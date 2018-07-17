
//This is the professors unedited js code for hte colours

var urlBase = 'ameade.us'; //url will be changed to whatever godaddy host url is
var extension = "php";

//this is a test

var userId = 0;
var firstName = "";
var lastName = "";
var username = "";
var data = "";
var jsonDatabase = "";
var createList = "";
var display = false;
var lastClickedForDelete = "";

//create username and password
function createUser()
{
    var newUser = document.getElementById("newUser").value; //taking up the user new name. it should check if the name is not taken yet
    var newPassword = document.getElementById("newPassword").value; //this is a varible to take the password to up. Next step is to make sure the password meet critera (at least 8 character, upper, lower, number and symbol,
    newPassword = sha1(newPassword);

    var jsonPayload = '{"Username" : "' + newUser + '", "Password" : "' + newPassword + '"}';
    var url = '/addUser.' + extension;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
    try
    {
        xhr.onreadystatechange = function()
        {
            if (this.readyState == 4 && this.status == 200)
            {
                //Change id to w/e we end up making the div for placing the confirmation
                document.getElementById("loginResult").innerHTML = "User has been added";
            }
        };
        xhr.send(jsonPayload);
    }
    catch(err)
    {
        //Change id to w/e we end up making the div for placing the confirmation
        document.getElementById("loginResult").innerHTML = err.message;
    }
}

function listTopGamers() //add the top 10 players globally (not fully done)
{
    var ul = document.getElementById("myUL");
    console.log(data[0]);
    for( var i = 0; i < data.length; i++ )
    {
      createList = data[i].split(",");
      var li = document.createElement("li");
      li.onmousedown = 'displayInfo(this)';
      li.value = i;
      li.addEventListener("click", function()
      {
            displayInfo(this.value);
      });
      var a = document.createElement('a');
      a.href = '#';
      a.appendChild(document.createTextNode(createList[2] + ",\t"
        + createList[3]));
      li.appendChild(a);
      //li.style = 'border: 1px solid #ddd; margin-top: -1px; /* Prevent double borders */ background-color: #f6f6f6; padding: 12px;text-decoration: none;font-size: 18px;color: black;display: block;'
      ul.appendChild(li);
  }
}

function doLogin()
{
	userId = 0;
	firstName = "";
	lastName = "";

	var login = document.getElementById("loginName").value;
	var password = document.getElementById("loginPassword").value;

    //add the sha1 to take the password and send it off to the server
    password = sha1(password);

	document.getElementById("loginResult").innerHTML = "";

	var jsonPayload = '{"Username" : "' + login + '", "Password" : "' + password + '"}';
	var url = /*urlBase +*/ '/Loginp.' + extension;

    console.log(jsonPayload);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, false);
	xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
	try
	{
		xhr.send(jsonPayload);
		console.log("sent correctly.");
		var jsonObject = String(JSON.parse(xhr.responseText));
        console.log(jsonObject);
		userId = Number(jsonObject.charAt(1));
        console.log(userId);

		if( userId < 1 )
		{
			document.getElementById("loginResult").innerHTML = "User/Password combination incorrect";
			return;
		}

		//username = jsonObject.Username;
		data = jsonObject.replace(/"/g, "").replace(/\s/g, "").split("?");

		//document.getElementById("userName").innerHTML = firstName + " " + lastName;
		document.getElementById("loginName").value = "";
        document.getElementById("loginPassword").value = "";

        hideOrShow( "loginDiv", false);
        hideOrShow( "contactDiv", true);
	}
	catch(err)
	{
		document.getElementById("loginResult").innerHTML = err.message;
	}
    listContacts();

}

function placeholderLogin(){
	//document.getElementById("userName").innerHTML = firstName + " " + lastName;
	document.getElementById("loginName").value = "";
	document.getElementById("loginPassword").value = "";

	hideOrShow( "loginDiv", false);
	hideOrShow( "contactDiv", true);
}

function doLogout()
{
	userId = 0;
	firstName = "";
	lastName = "";

	hideOrShow( "contactDiv", false); //change the tag
	hideOrShow( "loginDiv", true); //change the tag
}

function hideOrShow( elementId, showState )
{
	var vis = "visible";
	var dis = "block";
	if( !showState )
	{
		vis = "hidden";
		dis = "none";
	}

	document.getElementById( elementId ).style.visibility = vis;
	document.getElementById( elementId ).style.display = dis;
}

//the function to change the password to text to check the password
function ShowPass() {
    var x = document.getElementById("loginPassword");
    if (x.type == "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function sha1(msg)
//it have a 20 character salt added to the hash before sending this to the server
{
    //function used for later use
    function rotl(n,s)
    {
        return n<<s|n>>>32-s;
    };

    //function used for later use
    function tohex(i)
    {
        for(var h="", s=28;;s-=4)
        { h+=(i>>>s&0xf).toString(16);
            if(!s)
                return h;
        }
    };

    var H0=0x67452301, H1=0xEFCDAB89, H2=0x98B1DCFE, H3=0xA0325476, H4=0xC3D2E1F0, M=0x0ffffffff; //salt

    var i, t, W=new Array(80), ml=msg.length, wa=new Array();
    msg += String.fromCharCode(0x80);

    while(msg.length%4)
        msg+=String.fromCharCode(0);

    for(i=0;i<msg.length;i+=4)
        wa.push(msg.charCodeAt(i)<<24|msg.charCodeAt(i+1)<<16|msg.charCodeAt(i+2)<<8|msg.charCodeAt(i+3));

    while(wa.length%16!=14)
        wa.push(0);
        wa.push(ml>>>29), wa.push((ml<<3)&M);

    for( var bo=0;bo<wa.length;bo+=16 )
    {
        for(i=0;i<16;i++)
            W[i]=wa[bo+i];

        for(i=16;i<=79;i++)
            W[i]=rotl(W[i-3]^W[i-8]^W[i-14]^W[i-16],1);

        var A=H0, B=H1, C=H2, D=H3, E=H4;

        for(i=0 ;i<=19;i++)
            t=(rotl(A,5)+(B&C|~B&D)+E+W[i]+0x5A827999)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;

        for(i=20;i<=39;i++)
            t=(rotl(A,5)+(B^C^D)+E+W[i]+0x6ED9EBA1)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;

        for(i=40;i<=59;i++)
            t=(rotl(A,5)+(B&C|B&D|C&D)+E+W[i]+0x8F1BBCDC)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;

        for(i=60;i<=79;i++)
            t=(rotl(A,5)+(B^C^D)+E+W[i]+0xCA62C1D6)&M, E=D, D=C, C=rotl(B,30), B=A, A=t;

        H0=H0+A&M; H1=H1+B&M; H2=H2+C&M; H3=H3+D&M; H4=H4+E&M;
    }
    return tohex(H0)+tohex(H1)+tohex(H2)+tohex(H3)+tohex(H4);
}
