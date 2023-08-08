const btnReplySave = document.querySelector("#btn-reply-save");
if (btnReplySave !== null) {
	btnReplySave.addEventListener("click", async () => {
		const board_id = document.querySelector("#board-id").value;
		const member_id = document.querySelector("#member-id").value;				
		const content = document.querySelector("#reply-content").value;	
		
		const data = {
			board_id,
			member_id,						
			content,				
		};	
		
		const BASE_URL = "http://localhost:8000"
		const url = BASE_URL + `/api/board/${data.board_id}/reply`;	
		
		await sendAxiosPostReplyRequest(url, data);	
	});
}