const btnDelete = document.querySelector("#btn-delete");
if (btnDelete !== null) {
	btnDelete.addEventListener("click", async () => {
		const id = document.querySelector("#hidden-id").value;	
		
		const BASE_URL = "http://localhost:8000"
		const url = BASE_URL + "/api/board/" + id;	
		
		await sendAxiosDeleteRequest(url);	
	});
}
