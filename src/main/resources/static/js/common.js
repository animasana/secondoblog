async function sendFetchPostRequest(url, data) {
	try {    
    	const response = await fetch(url, {
	    	method: 'POST',
	    	headers: {
				Accept: "application/json",
	      		"Content-Type": "application/json",
	    	},
	    	body: JSON.stringify(data),
		});
    	
    	if (response.ok) {
			const res = await response.json();
			console.log(res);			
		}
  	} catch (error) {
    	console.log("Error:", error);
  	}  
}

async function sendFetchPutRequest(url, data) {
	try {    
    	const response = await fetch(url, {
	    	method: 'PUT',
	    	headers: {
				Accept: "application/json",
	      		"Content-Type": "application/json",
	    	},
	    	body: JSON.stringify(data),
		});
    	
    	if (response.ok) {
			const res = await response.json();
			console.log(res);			
		}
  	} catch (error) {
    	console.log("Error:", error);
  	}  
}

async function sendAxiosPostRequest(url, data) {
	try {    
    	const response = await axios.post(url, JSON.stringify(data), {
			headers: {
				"Content-type": "application/json",
			},
		});
		if (response.status === 200) {		
			console.log(response.data);			
			location.replace("/");
		}		
  	} catch (error) {
    	console.error("Error: ", error);
  	}  
}

async function sendAxiosPostReplyRequest(url, data) {
	try {    
    	const response = await axios.post(url, JSON.stringify(data), {
			headers: {
				"Content-type": "application/json",
			},
		});
		if (response.status === 200) {		
			console.log(response.data);			
			location.replace(`/board/${data.board_id}`);
		}		
  	} catch (error) {
    	console.error("Error: ", error);
  	}  
}

async function sendAxiosDeleteRequest(url) {
	try {
		const response = await axios.delete(url);
		
		if (response.status === 200) {
			console.log(response.data);
			//location.href = "/";
		}
	}
	catch (error) {
		console.error("Error: ", error);
	}
}

async function sendAxiosPutRequest(url, data) {
	try {    
    	const response = await axios.put(url, JSON.stringify(data), {
			headers: {
				"Content-type": "application/json",
			},
		});
		
		if (response.status === 200) {		
			console.log(response.data);			
			//location.href = "/";
		}		
  	} catch (error) {
    	console.error("Error: ", error);
  	}  	
}