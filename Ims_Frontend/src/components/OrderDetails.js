

export default function OrderDetails(propos) {


    return <div className="receipt-container">
        <div class="receipt">
            <div class="header">Order Details</div>
            <br /><br />
            <div class="order-info">
                <span>Order ID: <strong>{propos.order.Id}</strong></span>
                <span>Location ID: <strong>{propos.order.loc}</strong></span>
            </div>

            <div class="item">
                <span>Material Name: <strong className="materialName">{propos.order.mat}</strong></span>
                <br />
                <span>Quantity: <strong>{propos.order.qty}</strong></span>
            </div>

            <br /><br />

            <div class="thanks">Thanks for ordering!!</div>
            
        </div>
        
    </div>

}