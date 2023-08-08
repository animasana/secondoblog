const btnUpdateUser = document.querySelector("#btn-update-user");

if (btnUpdateUser !== null) {
	btnUpdateUser.addEventListener("click", async () => {
		const username = document.querySelector("#username").value;
		const password = document.querySelector("#pwd").value;
		const email = document.querySelector("#email").value;
		
		const user = {
			username,
			password,
			email, 		
		};	
		
		const BASE_URL = "http://localhost:8000"
		const url = BASE_URL + "/user/info";	
		
		await sendFetchPutRequest(url, user);	
	});
}
