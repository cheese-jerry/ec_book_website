function editCart(cartItemId,buyCount){
    window.location.href='cart.do?operate=editCart&cartItemId='+cartItemId+"&buyCount="+buyCount;
}

function delCart(cart){
    window.location.href='cart.do?operate=delCart&cart='+cart;
}

function delOneCartItem(cartItemid){
    window.location.href='cart.do?operate=delOneCartItem&cartItemid='+cartItemid;
}