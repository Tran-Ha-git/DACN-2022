<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<div class="main-content item">
	<div class="title">
		<h2 class="title-content">Cập nhật sách</h2>
	</div>


	<div class="list-book-container">
		<form action="/admin/editBook/${book.id}" method="post" enctype="multipart/form-data">
			<label>Tên sách</label> <input type="text" name="name"
				class="width-input" value="${book.name}" /><br /> <br /> <label>Upload
				hình</label><br /> <input type="file" name="fileUpload" class="width-input"
				value="${book.thumbnail}" /><br /> <br />

			<div class="form-lable-tyle">
				<div class="form-lable-3">
					<label>Trạng thái</label><br /> <select name="status"
						class="width-input">
				
						<option value="1">Hoàn thành</option>
						<option value="2">Đang cập nhật</option>
						<option value="0">Đã xóa</option>
					</select><br />
				</div>

				<div class="form-lable-3">
					<label>Loại</label><br /> <input type="checkbox"
						class="width-input"  value="false" name="vip" ${check}/> <label>Vip</label>

				</div>

				<div class="form-lable-3">
					<label>Giá</label><br /> <input type="text" name="price"
						class="width-input" value="${book.price}" />
				</div>
				<br />
				<div class="form-lable-3">
					<label>Slug</label><br /> <input type="text" name="slug"
						class="width-input" value="${book.slug}" />
				</div>
				<br />
			</div>

			<label>Meta title</label><br /> <input type="text" name="metaTitle"
				class="width-input" value="${book.metaDescription}" /><br /> <br /> <label>Meta
				Description</label> <input type="text" name="metaDescription"
				class="width-input" value="${book.metaDescription}" /><br /> <br />
			<label>Giới thiệu sách</label><br /> <input type="text"
				name="description" class="width-input" value="${book.description}">
			<br /> <br />

			<div class="listCheck_three">
				<div class="list-checkbox">
					<p>Tác giả</p>
					<div class="form-1">
						<c:forEach items="${book.authors}" var="author">
							<div class="form_input">
								<input type="radio" class="authorName" 
								    data-authorid="${author.id }"
								    data-authorname="${author.fullname }"/>
								<input type="text" 
							    class="edit-authorname-input"
								value="${author.fullname}"
								id="authorName${author.id }"
								 style="width:140px" disabled/>
								 
								 <br />
							</div>
						</c:forEach>

					</div>
				</div>
				<div class="list-checkbox">
					<p>Thể loại</p>
					<div class="form-1">
						<c:forEach items="${book.categories}" var="category">
							<div class="form_input">
							
							<input class="categoryName"
							type="radio" 
								    data-categoryid="${category.id }"
								    data-categoryname="${category.name }"/>
								<input type="text" 
							    class="edit-categoryname-input"
								value="${category.name}"
								id="categoryName${category.id }"
								 style="width:140px" disabled />
							</div>
						</c:forEach>
					</div>
				</div>
				
				<div class="list-checkbox pdf-links">
					<p>Link pdf</p>
					<div class="form-1">
						<c:forEach items="${book.pdfs}" var="pdf">
							<div class="form_input">
								<a href="${pdf.url }">${pdf.url }</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="lable-tyle">
				<br />
				<p>Nội dung</p>

				<div class="btn_two">
					<div class="btn_two_content">
						<c:url value="/admin/editBook/listChapter" var="url">
							<c:param name="id" value="${book.id}" />
						</c:url>
						<a href="${url}" class="grey">Cập nhật danh sách chương</a>
						<c:url value="/admin/editBook/listAudio" var="url">
							<c:param name="id" value="${book.id}" />
						</c:url>
						<a href="${url}" class="grey">Cập nhật danh sách Audio</a>
					</div>
					
				</div>
			</div>

			<div class="btn spacing">
				<a href="" class="grey">Hủy</a> <input type="submit"
					value="Cập nhật" class="black">
			</div>
		</form>
	</div>

</div>
<script>
var authorNames = document.querySelectorAll('.authorName');

authorNames.forEach(name =>{
	var authorId= name.dataset.authorid;
	var authorName = name.dataset.authorname;

	name.onclick= function(){
		var authorInput = document.querySelector("#authorName"+authorId);
	
		authorInput.removeAttribute('disabled');
		authorInput.setAttribute('name', 'fullname');
	
	}
	
})

var categoryNames = document.querySelectorAll('.categoryName');

categoryNames.forEach(name =>{
	var categoryId= name.dataset.categoryid;
	var categoryName = name.dataset.categoryname;

	name.onclick= function(){
		var categoryInput = document.querySelector("#categoryName"+categoryId);
		categoryInput.removeAttribute('disabled');
		categoryInput.setAttribute('name', 'categoryName');
		
	}
	
})

</script>
