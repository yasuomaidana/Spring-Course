// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
    $('#users').DataTable();
});

async function loadUsers(){//Since we used await we have to define this function as an asynchronic
    const request = await fetch("user/123",{//await make request to wait the answer, all the program halts until the function is resolved
        method: 'GET', 
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        }
    });
    const user = await request.json();
    console.log(user);

}