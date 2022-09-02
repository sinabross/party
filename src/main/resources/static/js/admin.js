/**
 * 관리자기능
 * 
 */
let editor;
let url;
let notice_type_;

// 전체선택
var allCheck = function(){
	//만약 전체 선택 체크박스가 체크된상태일경우 
	if($("#allCheck").prop("checked")) { 
		//해당화면에 전체 checkbox들을 체크해준다 
		$(".notice-idx-check").prop("checked",true); 
		// 전체선택 체크박스가 해제된 경우 
	}else { 
		//해당화면에 모든 checkbox들의 체크를해제시킨다. 
		$(".notice-idx-check").prop("checked",false); 
	} 
}

// 목록에서 삭제
var deleteNotice = function(){
	
	var delete_notice_idx_list = [];
	$(".notice-idx-check").each(function(index, data){
		if($(this).prop('checked')){
			delete_notice_idx_list.push($(this).val());
		}
	});
	
	// validataion
	if(delete_notice_idx_list.length == 0){
		alert('삭제할 목록을 선택해주세요.');
		return;
	}
	
	if(confirm('삭제하시겠습니까?')){
		
		var formData = new FormData();
		formData.append('delete_notice_idx_list', delete_notice_idx_list.toString());
		
		$.ajax({
	        type:"POST",
	        url:"/admin/notice/deleteNotice",
	        data: formData,
	        processData: false,
            contentType: false,
	        success:function(response) {
				if(response.result == 'success'){
					alert('삭제 성공.');
					list(1);
				} else{
					alert('삭제 실패.');
				}        	
	        },
	        error:function(response, status, error) {
	            alert('deleteNotice error.' + error);
	        }
	    });
	}
	
}

//상세보기에서 삭제
var deleteNoticeView = function(){
	
	if(confirm('삭제하시겠습니까?')){
		
		var delete_notice_idx_list = [];
		delete_notice_idx_list.push($('#mainForm #notice_idx').val());
		
		var formData = new FormData();
		formData.append('delete_notice_idx_list', delete_notice_idx_list.toString());
		
		$.ajax({
	        type:"POST",
	        url:"/admin/notice/deleteNotice",	    
	        data: formData,
	        processData: false,
            contentType: false,
	        success:function(response) {
				if(response.result == 'success'){
					alert('삭제 성공.');
					btnList();
				} else{
					alert('삭제 실패.');
				}        	
	        },
	        error:function(response, status, error) {
	            alert('deleteNotice error.' + error);
	        }
	    });
	}
	
}

// 등록
var btnRegistration = function(){
	$('#notice_idx').val(0);
	$('#mainForm').attr('method','POST');
	$('#mainForm').attr('action', '/admin/notice/write').submit();
}
// 수정
var btnModify = function(){
	$('#mainForm').attr('method','POST');
	$('#mainForm').attr('action', '/admin/notice/write').submit();
}

// 등록/수정 화면 최초 진입시 게시판 타입에 따라 목록화면 URL 및 title 정의
var setPage = function(notice_type){
	
	notice_type_ = notice_type;
	
	if(notice_type == 'NTC01'){
		url = '/policy/reference';		// 정책자료실
		$('#title1').html('정책자료실');
		$('#title2').html('&gt; 정책 &gt; 정책자료실');
	}else if(notice_type == 'NTC02'){
		url = '/policy/commitment';	// 공약	
		$('#title1').html('정책');
		$('#title2').html('&gt; 정책 &gt; 공약');
	}else if(notice_type == 'NTC03'){
		url = '/news/notice';			// 공지사항
		$('#title1').html('공지사항');
		$('#title2').html('&gt; 소식 &gt; 공지사항');
	}else if(notice_type == 'NTC04'){
		url = '/news/report';			// 보도자료
		$('#title1').html('보도자료');
		$('#title2').html('&gt; 소식 &gt; 보도자료');
	}else if(notice_type == 'NTC05'){
		url = '/news/comment';			// 논평
		$('#title1').html('논평');
		$('#title2').html('&gt; 소식 &gt; 논평');
	}else if(notice_type == 'NTC06'){
		url = '/news/briefing';			// 브리핑
		$('#title1').html('브리핑');
		$('#title2').html('&gt; 소식 &gt; 브리핑');
	}else if(notice_type == 'NTC07'){
		url = '/news/data';				// 홍보
		$('#title1').html('홍보');
		$('#title2').html('&gt; 소식 &gt; 홍보');
	}else if(notice_type == 'NTC08'){
		url = '/party/community';
		$('#title1').html('당원게시판');
		$('#title2').html('&gt; 당원 &gt; 당원게시판');
	}
}

var loadEditor = function(data){
	CKEDITOR.replace('notice_content', {height: 500});
	
	if(data != null){
		CKEDITOR.instances.notice_content.setData(data.notice_content);
	}
};


var loadEditor2 = function(data){
	ClassicEditor
    .create( document.querySelector( '#notice_content' ), {
    	/*** 에디터 업로드 시작 **/
    	// extraPlugins: [ MyCustomUploadAdapterPlugin ]
    	/*** 에디터 업로드 종료 **/
    	//---------------------------------------------------------------- // 여기 툴바 부분의 옵션명을 넣어주면 원하는 설정을 할수 있습니다. 
    	toolbar: [ 
    		'undo', 
        	'redo', 
        	'alignment:left', 
        	'alignment:right', 
        	'alignment:center', 
        	'alignment:justify', 
        	'alignment', 
        	'fontSize', 
        	'fontFamily', 
        	'highlight:yellowMarker', 
        	'highlight:greenMarker', 
        	'highlight:pinkMarker', 
        	'highlight:blueMarker', 
        	'highlight:redPen', 
        	'highlight:greenPen', 
        	'removeHighlight', 
        	'highlight', 
        	'bold', 
        	'italic', 
        	'strikethrough', 
        	'underline', 
        	'blockQuote', 
        	//'ckfinder', 
        	//'imageTextAlternative', 
        	//'imageUpload', 
        	'heading', 
        	//'imageStyle:full', 
        	//'imageStyle:alignLeft', 
        	//'imageStyle:alignRight', 
        	'link', 
        	'numberedList', 
        	'bulletedList', 
        	'mediaEmbed', 
        	'insertTable', 
        	'tableColumn', 
        	'tableRow', 
        	'mergeTableCells', 
        	'indent', 
        	'outdent', 
        	'fontColor', 
        	'fontBackgroundColor', 
        	'code'
    	], 
    	//---------------------------------------------------------------- 
    	heading: { 
    		options: [ 
    			{ model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' }, 
    			{ model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' }, 
    			{ model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' } 
    		] 
    	}
    })
    .then(function(newEditor){
    	editor = newEditor;
    	if(data != null){
    		newEditor.setData(data.notice_content);
    	}
    })
    .catch(function(error){
        console.error( error );
    });
    //.then(newEditor => {
    //	editor = newEditor;
    //	if(data != null){
    //		newEditor.setData(data.notice_content);
    //	}
    //})
    //.catch( error => {
    //    console.error( error );
    //} );
}

// 저장
var btnSave = function(data){
	
	if($.trim($('#notice_title').val()) == ''){
		alert('제목을 입력하세요.');
		$('#notice_title').focus();
		return;
	}
	if($.trim(CKEDITOR.instances.notice_content.getData()) == ''){
		alert('내용을 입력하세요.');
		$('#notice_content').focus();
		return;
	}
	
	//TODO - 게시판에 따른 확장자 체크필요	
	var formData = new FormData();
	formData.append('notice_type', notice_type_);
    formData.append('notice_title', $('#notice_title').val());
    formData.append('notice_content', CKEDITOR.instances.notice_content.getData());
    if(notice_type_ == 'NTC01'){
    	formData.append('division', $('#division').val());
    }
    if(notice_type_ == 'NTC02'){
    	formData.append('division', $('#division').val());
    }
    if(notice_type_ == 'NTC03'){
    	formData.append('division', '공지');
    }
    
    $("#uploadResult li").each(function(i, obj) {
    	var jobj = $(obj);

		formData.append('attachList[' + i + '].notice_type', jobj.data('notice-type'));
		formData.append('attachList[' + i + '].real_file_name', jobj.data('real-file-name'));
		formData.append('attachList[' + i + '].upload_file_path', jobj.data('notice-type') + "/" + jobj.data('uuid'));
	});
	
	var url = '/admin/notice/addProc';
	
	if(data != null){
		formData.append('notice_idx', $('#mainForm #notice_idx').val());
		url = '/admin/notice/modifyProc';
	}
	
	
	$.ajax({
        type: 'POST',
        url: url,	    
        data: formData,
        processData: false,
        contentType: false,
        success:function(response) {
        	if(response.result == 'success'){
        		alert('등록되었습니다.');
        		btnList();
        	}else{
        		alert('등록에 실패하였습니다.');
        	}
        },
        error:function(response, status, error) {
            alert('btnSave error.' + error);
        }
    });
}

// 목록화면으로 이동
var btnList = function(){
	$('#mainForm').attr('method','POST');
	$('#mainForm').attr('action', url).submit();
}


