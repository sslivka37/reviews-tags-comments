/*const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(this.readyState == 4 && this.status == 200){
		const res = JSON.parse(xhr.response)
		const container = document.querySelector('.container')


		console.log({res})

		res.forEach(function(review) {
			const reviewItem = document.createElement('div')
			const name = document.createElement('h2')
			name.innerText = review.name;

			container.appendChild(reviewItem)
			reviewItem.appendChild(name);

		});
	}
}

xhr.open('GET', 'http://localhost:8080/', true)
xhr.send()