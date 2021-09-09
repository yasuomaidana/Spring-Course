async function login(){
    let email = $("#email").val();
    let password = $("#password").val();
    let data ={
        "email":email,
        "password":password
    };
    const request = await fetch('api/login',{
        method:'POST',
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
    });
    const response = await request.json();
}