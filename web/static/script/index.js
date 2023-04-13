function addCart(bookId){
    window.location.href="cart.do?operate=addCart&bookId="+bookId; //跳转到cart控制器调用addCart方法参数是bookId
}

/*
function search(){
    var bookname = document.getElementById("search").value+"";
    window.location.href="book.do?operate=search&bookname="+bookname;
}

 */