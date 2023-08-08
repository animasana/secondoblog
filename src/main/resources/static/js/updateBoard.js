/**
 * 
 */
const btnUpdate = document.querySelector("#btn-update");
if (btnUpdate !== null) {
	btnUpdate.addEventListener("click", async () => {
		const id = document.querySelector("#hidden-id").value;
		const title = document.querySelector("#title").value;
		const content = document.querySelector("#content").value;
		
		const updatedJson = {
			id, 
			title,
			content,			
		}
		
		const BASE_URL = "http://localhost:8000";
		const url = BASE_URL + "/api/board";
		
		await sendAxiosPutRequest(url, updatedJson);
	});
}