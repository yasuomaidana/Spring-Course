// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
    $('#users').DataTable();
});

convertToRow=(users)=>{
    let row = "";
    users.forEach(user=>{
        let button = `<button class="btn btn-danger btn-circle btn-sm" onclick="deleteUser(${user.id})"><i class="fas fa-trash"></i></button>`;
        user.phone = user.phone==null ? '-':user.phone;
        let rowContent =`<td>${user.id}</td><td>${user.name} ${user.lastname}</td><td>${user.email}</td><td>${user.phone}</td>
        <td>${button}</td>`
        let tr = `<tr>${rowContent}</tr>`;
        row+=tr;
    });
    return row;
}
async function loadUsers(){//Since we used await we have to define this function as an asynchronic
    const request = await fetch("api/users",{//await make request to wait the answer, all the program halts until the function is resolved
        method: 'GET', 
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        }
    });
    const users = await request.json();
    document.querySelector('#users tbody').outerHTML = convertToRow(users);
}

async function deleteUser(id){
    if(!confirm('Do you want to delete this user?')){
        return;
    }
    const request = await fetch(`api/user/${id}`,{
        method: 'DELETE', 
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        }
    });
    location.reload();
}