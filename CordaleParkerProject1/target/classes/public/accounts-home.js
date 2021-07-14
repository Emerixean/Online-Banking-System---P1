const url = "account";
 
window.onload = function(){
	console.log("Inside onload function!");
	grabAccounts();

}



function withdraw(){
	
	
	let xhr = new XMLHttpRequest();
	
	let accountNumber = document.getElementById("accountNumber").value;
	let amount = document.getElementById("amount").value;

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
					resetTable();
					grabAccounts();
				}
				if(xhr.status == 401){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "You must login to access our services";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
				
				if(xhr.status == 405){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "Invalid account selected";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
				if(xhr.status == 404){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "Account does not exist";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
		}
		
		
		
	}
	
	xhr.open("PUT","accountwithdrawl");
	
	let transaction = {};
	transaction.accountNumber = accountNumber;
	transaction.amount = amount;
	xhr.send(
		JSON.stringify(transaction)
	);
	
}

function deposit(){
	
	
	let xhr = new XMLHttpRequest();
	
	let accountNumber = document.getElementById("accountNumber").value;
	let amount = document.getElementById("amount").value;
	
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
					resetTable();
					grabAccounts();
				}
				if(xhr.status == 401){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "You must login to access our services";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
				
				if(xhr.status == 405){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "Invalid account selected";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
				
				if(xhr.status == 404){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "Account does not exist";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
		}
		
		
		
	}
	
	xhr.open("PUT","accountdeposit");
	
	let transaction = {};
	transaction.accountNumber = accountNumber;
	transaction.amount = amount;
	xhr.send(
		JSON.stringify(transaction)
	);
	
}

function transfer(){
	
	
	let xhr = new XMLHttpRequest();
	
	let accountNumber = document.getElementById("accountNumber").value;
	console.log()
	let accountNumber2 = document.getElementById("accountNumber2").value;
		console.log()
	let amount = document.getElementById("amount").value;
	
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
					resetTable();
					grabAccounts();
				}
				if(xhr.status == 401){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "You must login to access our services";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}
				
				if(xhr.status == 405){
				
				let oldMessage = document.getElementById("error");
				
				if(oldMessage){
				oldMessage.remove();
				}
				
				let errorMessage = document.createElement("p");
				errorMessage.setAttribute("id","error");
				errorMessage.innerHTML = "Invalid account selected";
				
				let attachPoint = document.getElementById("input-field");
				
				attachPoint.appendChild(errorMessage);
				}				
				

		}
		
		
		
	}
	
	xhr.open("PUT","accounttransfer");
	
	let transaction = {};
	transaction.accountNumber = accountNumber;
	transaction.accountNumber2 = accountNumber2;
	transaction.amount = amount;
	console.log(transaction);
	xhr.send(
		JSON.stringify(transaction)
	);
	
}

function grabAccounts(){
	
	let xhr = new XMLHttpRequest();

	const url = "account";
	
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
					console.log(xhr.responseText);
					
					let accountList = JSON.parse(xhr.responseText);
					
					console.log(accountList);
					
					accountList.forEach(
						element => {
							addRow(element);
						}
						
					)

				}
		}
		
		
		
	}
	
	xhr.open("GET",url);
		xhr.send();
}



function addRow(account){
	
	
	let table = document.getElementById("account-table")
	
	let tableRow = document.createElement("tr");
	let accNumCol = document.createElement("td");
	let typeCol = document.createElement("td");
	let balanceCol = document.createElement("td");
	let approvedCol = document.createElement("td");

	
	
	tableRow.appendChild(accNumCol);
	tableRow.appendChild(typeCol);
	tableRow.appendChild(balanceCol);
	tableRow.appendChild(approvedCol);

	
	table.appendChild(tableRow);
	
	accNumCol.innerHTML = account.accountNumber;
	typeCol.innerHTML = account.accountType
	// nameCol.setAttribute("id",planet.id + "name");
	
	balanceCol.innerHTML = account.accountBalance;
	
	approvedCol.innerHTML = account.accountApproved;

	

	
	};
	

function resetTable(){


let table =document.getElementById("account-table")

while(table.rows.length > 1) {
  table.deleteRow(1);
}



}
	

