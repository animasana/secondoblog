/**
 * 
 */

const btnSave = document.querySelector("#btn-save");
if (btnSave !== null) {
	btnSave.addEventListener("click", async () => {
		const title = document.querySelector("#title").value;
		const content = document.querySelector("#content").value;	
		
		const data = {
			title,
			content,				
		};	
		
		const BASE_URL = "http://localhost:8000"
		const url = BASE_URL + "/api/board";	
		
		await sendAxiosPostRequest(url, data);	
	});
}
