import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Orders(props){

    const login = sessionStorage.getItem('login');

    const [orders,setOrders] = useState();

    const [msg, setMsg] = useState('');

    const [flag,setFlag] = useState(false);
    const [msg1, setMsg1] = useState('');

    const url = sessionStorage.getItem('url');
    
    const getOrders = () => {
        console.log(url + '/getOrders/'+sessionStorage.getItem('userID'));
        fetch(url + '/getOrders/'+sessionStorage.getItem('userID'),
           {method : 'get'}
        ).then(data => {
            if(data.ok){
                
                data.json().then(data => displayOrders(data));
            } else {
                setFlag(false);
                setMsg("No Orders");
                setOrders('');
            }
        })
    } 

    useEffect(() => {
        getOrders();
    },[])

    const handleCancel = (orderId) => {
        setFlag(true);
        fetch(url + '/cancelOrder/'+orderId,
           {method : 'post'}
        ).then(data => data.json()).then(data => {setMsg1(data.msg); getOrders();});
        
    }

    const displayOrders = (data) => {
        setOrders(
            data.map((data) => 
                <tr>
                    <td>{data.orderId}</td>
                    <td>{data.localDate.slice(0,10)}</td>
                    <td>{data.localTime}</td>
                    <td>{data.locationNumber}</td>
                    <td>{data.materialId}</td>
                    <td>{data.orderQuantity}</td>
                    <td>{data.orderStatus}</td>
                    <td><button onClick={() => handleCancel(data.orderId)}>cancel</button></td>
                </tr>
            )
        )
    }


    return login ? 
        
        <div className="orders">
            <table>
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Location Number</th>
                    <th>Material Name</th>
                    <th>Order Quantity</th>
                    <th>Order Status</th>
                </tr>
                </thead>
                <tbody>
                    {orders}
                </tbody>
            </table>
            <br/><br/>
        <div id="msg">{flag ? msg1:msg}</div>
            </div> :
                window.location.href = '/login';

}