$(document).ready(function(){
	$("#reg-form").on('submit', function(event){
		event.preventDefault();
		console.log("woring");
		let form = new FormData(this);
		console.log(form);
		$.ajax({
			url:"SignupServlet",
			type:"POST",
			data:form,
			success:function(data){
				console.log("data"+data)
				
				if(data.trim()==='done'){
					swal("Registered Successfully..!", "Redirecting you to login page..!", "success")
				.then((value) => {window.location ="login.jsp"});
				}else{
					swal(data);
				}
				

			},
			error:function(data){
				console.log("error "+data)
				swal("Something went wrong..!", "Please try again..!", "warning");
			},
			processData:false,
			contentType:false,
			
		})
		
	})
})


//edit profile
$(document).ready(function(){
	var editStatus= false
	$("#edit-profile-btn").click(function(){
		console.log("working")
		if(editStatus== false){
			$("#user-details").hide()
			$("#user-edit").show()
			editStatus= true
			$(this).text("Back")
		}else{
			$("#user-details").show()
			$("user-edit").hide()
			$(this).text("Edit")
		}
	})
})



//add blog
$(document).ready(function (){
	$("#add-blog").on('submit', function(event){
		event.preventDefault();
		let form = new FormData(this);
		
		$.ajax(
			{
				url:"AddBlogServlet",
				type:"post",
				data:form,
				success:function(data){
					console.log(data)
					if(data.trim()==='done'){
					swal("Blog Posted Successfully..!", "Redirecting you to login page..!", "success")
					.then((value) => {window.location ="profile.jsp"});
					}else{
						swal(data, "warning");
				}
				},
				error:function(data){
				console.log(data)
				swal("Something went wrong..!", "Please try again..!", "warning")
				},
				processData: false,
    			contentType: false,

				
			})
	})
})
function getPost(catId, temp){
	$(".c-link").removeClass("active")
	$("#rloder").show()
	$("#blog-data").hide()
	$.ajax(
		{
			url:"loadblogs.jsp",
			data:{cid:catId},
			success:function(data){
				$("#rloder").hide()
				$("#blog-data").show()
				$("#blog-data").html(data)
				$(temp).addClass("active")
			},
			
		}
	)
	
}

$(document).ready(function(){
	var letallpost = $(".c-link")[0]
	getPost(0, letallpost)
})

function likePost(pid, uid){
	const d={
		blog:pid,
		user:uid,
		operation:'like',
	}
	
	$.ajax({
		url:"LikeServlet",
		data:d,
		success:function(data){
			let c = $(".like-counter").html()
			c++
			$(".like-counter").html(c)
		},
		error:function(data){
			console.log(data)
		}
	})
}

