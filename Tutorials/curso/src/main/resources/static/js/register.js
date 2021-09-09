$(document).ready(()=>{
    //On ready
});
async function registerUser(){
    let data = {};
    let name =document.getElementById('name').value;
    data.name = name != "" ? name:null;
    let lastname =document.getElementById('lastname').value;
    data.lastname = lastname != "" ? lastname:null;
    data.email = document.getElementById('email').value;
    data.password = document.getElementById('password').value;
    
    let password_confirm = document.getElementById('password_confirm').value;
    if(password_confirm!=data.password){
        alert('The passwords are different');
        return;
    }
    const request = await fetch('api/user',{
        method:'POST',
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        //JSON.stringify(whatever) convert any javascript object into a json string
        body:JSON.stringify(data)
    })
    
}
