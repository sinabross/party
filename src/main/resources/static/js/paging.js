/**
 * 페이징 (PC / Mobile 공통)
 */
var paging = function(totalData, currentPage, targetId){
	
    var totalPage = Math.ceil(totalData/dataPerPage);    //총 페이지 수
    var pageGroup = Math.ceil(currentPage/pageCount);    //페이지 그룹
    
    var last = pageGroup * pageCount;    //화면에 보여질 마지막 페이지 번호
    if(last > totalPage){
        last = totalPage;
    }
    
    var first = last - (pageCount-1);    //화면에 보여질 첫번째 페이지 번호
    if(first < 0){
    	first = 1;
    }
    
    var next = last+1;
    var prev = first-1;
    
    var html = '<ul class="pagination">';
    if(prev > 0){
        html += '<li class="page-item"><a class="page-link" href="javascript:list(' + prev + ')" id="prev"><i class="fas fa-angle-left"></i></a></li>';
    }
    
    for(var i=first; i <= last; i++){
    	if(i != currentPage){
        	html += '<li class="page-item"><a class="page-link" href="javascript:list(' + i + ')" id="'+i+'">'+i+'</a></li>';
    	}else{
        	html += '<li class="page-item active"><a class="page-link" href="javascript:list(' + i + ')" id="'+i+'">'+i+'</a></li>';
    	}
    }
    
    if(last < totalPage){
       	html += '<li class="page-item"><a class="page-link" href="javascript:list(' + next + ')" id="next"><i class="fas fa-angle-right"></i></a></li>';
    }
    html += '</ul>';
    
    $('#' + targetId).html(html);    //페이지 목록 생성
    
                                       
}
		