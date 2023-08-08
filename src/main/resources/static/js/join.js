const btnSave = document.querySelector("#btn-save");

if (btnSave !== null) {
	btnSave.addEventListener("click", async () => {
		const username = document.querySelector("#username").value;
		const password = document.querySelector("#pwd").value;
		const email = document.querySelector("#email").value;
		
		const user = {
			username,
			password,
			email, 		
		};	
		
		const BASE_URL = "http://localhost:8000"
		const url = BASE_URL + "/user/join/process";	
		
		await sendAxiosPostRequest(url, user);	
	});
}
