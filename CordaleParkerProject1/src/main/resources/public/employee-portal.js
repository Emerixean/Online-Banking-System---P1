const url = "account";
 
window.onload = function(){

	grabAccounts();
    grabCustomers();

}


function grabAccounts(){
	
	let xhr = new XMLHttpRequest();

	const url = "allUnapprovedAccounts";
	
	xhr.onreadystatechange = function(){
		

		switch(xhr.readyState){
			
			case 0:
				console.log("nothing, not initalized yet!");
				break;
			case 1: 
				console.log("connection established");
				break;
			case 2:
				console.log("request sent");
				break;
			case 3:
				console.log("awaiting request");
				break;
			case 4: 
				console.log(xhr.status)
				
				if(xhr.status == 200){
					
					let accountList = JSON.parse(xhr.responseText);
					

					
					accountList.forEach(
						element => {
							addRowAccount(element);
						}
						
					)

				}
		}
		
		
		
	}
	
	xhr.open("GET",url);
		xhr.send();
}

function grabCustomers(){
	
	let xhr = new XMLHttpRequest();

	const url = "allUnapprovedCustomerAccounts";
	
	xhr.onreadystatechange = function(){
		

		switch(xhr.readyState){
			
			case 0:
				console.log("nothing, not initalized yet!");
				break;
			case 1: 
				console.log("connection established");
				break;
			case 2:
				console.log("request sent");
				break;
			case 3:
				console.log("awaiting request");
				break;
			case 4: 

				
				if(xhr.status == 200){

					
					let accountList = JSON.parse(xhr.responseText);
					
	
					
					accountList.forEach(
						element => {
							addRowCustomer(element);
						}
						
					)

				}
		}
		
		
		
	}
	
	xhr.open("GET",url);
		xhr.send();
}

function addRowAccount(account){
	
	
	let table = document.getElementById("account-table")
	
	let tableRow = document.createElement("tr");
	let accNumCol = document.createElement("td");
	let typeCol = document.createElement("td");
	let balanceCol = document.createElement("td");
	

	
	
	tableRow.appendChild(accNumCol);
	tableRow.appendChild(typeCol);
	tableRow.appendChild(balanceCol);


	
	table.appendChild(tableRow);
	
	accNumCol.innerHTML = account.accountNumber;
	typeCol.innerHTML = account.accountType
	// nameCol.setAttribute("id",planet.id + "name");
	
	balanceCol.innerHTML = account.accountBalance;
	


	

	
	};
	
	function addRowCustomer(customer){
	
	
		let table = document.getElementById("customer-table")
		
		let tableRow = document.createElement("tr");
		let idCol = document.createElement("td");
		let firstNameCol = document.createElement("td");
		let lastNameCol = document.createElement("td");
		let emailCol = document.createElement("td");
		let phoneNumberCol = document.createElement("td");
		
	
		
		
		tableRow.appendChild(idCol);
		tableRow.appendChild(firstNameCol);
		tableRow.appendChild(lastNameCol);
		tableRow.appendChild(emailCol);
		tableRow.appendChild(phoneNumberCol);
	
	
		
		table.appendChild(tableRow);
		
		idCol.innerHTML = customer.id;
		firstNameCol.innerHTML = customer.firstName;
		lastNameCol.innerHTML= customer.lastName;
		emailCol.innerHTML = customer.email;
		phoneNumberCol.innerHTML=customer.phoneNumber;
		
	
	
		
	
		
		};
function resetTable(){


let table =document.getElementById("account-table")

while(table.rows.length > 1) {
  table.deleteRow(1);
}



}
	

