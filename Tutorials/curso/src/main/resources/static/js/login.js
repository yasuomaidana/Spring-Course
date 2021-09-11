async function login(){

    let data ={
        "email":$("#email").val(),
        "password":$("#password").val()
    };
    const request = await fetch('api/login',{
        method:'POST',
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    });
    const response = await request.text();
    if(response !="Fail"){ 
        localStorage.setItem("token",response);
        localStorage.setItem("email",data.email);
        window.location.href="users.html";
    }
    else alert("Wrong credentials 🔥😡🔥");
}