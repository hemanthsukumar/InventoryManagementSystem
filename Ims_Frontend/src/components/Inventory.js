import { useState } from "react"
import OrderDetails from "./OrderDetails";
import userEvent from "@testing-library/user-event";

export default function Inventory(props) {

    const [locNum, setLocNum] = useState('');
    const [matID, setMatID] = useState('');
    const [inv, setInv] = useState();
    const [msg, setMsg] = useState('');
    const [flag, setFlag] = useState(false);
    const [msg1, setMsg1] = useState('');
    const [disable, setDisable] = useState(false);
    const [order, setOrder] = useState({
        Id: "",
        loc: "",
        mat:"",
        qty: ""
    })
    var count = 1;
    const [ordered,setOrdered] = useState(false);

    const url = sessionStorage.getItem('url');



    const setInvenotory = (data) => {
        if (matID === '') {
            setInv(data.map((data) => <tr>
                <td>{data.locationNumber}</td>
                <td>{data.materialNumber}</td>
                <td>{data.availableQuantity}</td>
                <td><input className="field" type='number' id={'qnt' + data.materialNumber} onChange={(e) => data.availableQuantity = e.target.value} required /></td>
                <td><button id={data.materialNumber} disabled={data.availableQuantity === 0 ? true : false} onClick={() => handleOrder(data.locationNumber, data.materialNumber, 'qnt' + data.materialNumber)}>Order</button></td>
            </tr>))
        } else
            setInv(
                <tr>
                    <td>{data.locationNumber}</td>
                    <td>{data.materialNumber}</td>
                    <td>{data.availableQuantity}</td>
                    <td><input className="field" type='number' id={'qnt' + data.materialNumber} onChange={(e) => data.availableQuantity = e.target.value} required /></td>
                    <td><button id={data.materialNumber} disabled={data.availableQuantity === 0 ? true : false} onClick={() => { handleOrder(data.locationNumber, data.materialNumber, 'qnt' + data.materialNumber) }}>Order</button></td>
                </tr>
            )
    }

    const getInv = () => {

        if (matID === '')
            fetch(url + '/getInventorys/' + locNum)
                .then(data => {
                    if (data.ok) {
                        setMsg('');
                        data.json().then(data => setInvenotory(data));

                    }
                    else {
                        setFlag(false);
                        setInv('');
                        setMsg("No Data Found");
                    }
                })
        else
            fetch(url + '/getInventory/' + locNum + '/' + matID)
                .then(data => {
                    if (data.ok) {
                        setMsg('');
                        data.json().then(data => setInvenotory(data));
                    }
                    else {
                        console.log(124)
                        setFlag(false);
                        setInv('');
                        setMsg("No Data Found");
                    }
                })

    }

    const handleOrder = (l, m, id) => {


        document.getElementById(m).disabled = true;

        var q = document.getElementById(id).value;
        if (q > 0 && q % 1 === 0) {
            fetch(url + '/order/' + sessionStorage.getItem('userID') + '/' + l + '/' + m + '/' + q, {
                method: 'post'
            })
                .then(data => data.json()
                    .then(data => {
                        if(data.msg != "Order Quantity should be less than avalable Quantity"){
                        setMsg1(data.msg + ' *** ' + m + ', Quantity ' + q + ' *** ');
                        getInv();
                        document.getElementById(m).disabled = false;
                        setOrder({
                            Id: ++count,
                            loc: l,
                            mat: m,
                            qty: q
                        })
                        
                        setOrdered(true);
                    }else{
                        setMsg1(data.msg);
                        document.getElementById(m).disabled = false;

                    }
                    }));
        } else {
            alert("Order Qantity should be greater than Zero and Integer");
            document.getElementById(m).disabled = false;
        }

    }



    const handleSubmit = (e) => {
        e.preventDefault();
        setFlag(true);
        setMsg1('');
        getInv();
    }


    return ordered ? <div id="orderdev"><OrderDetails order={order}></OrderDetails> <button onClick={()=>setOrdered(false)}>back</button></div>: <div className="inventory-contaner">
        <h1 className="name">Welcome {sessionStorage.getItem('name')}!!</h1>
        <form onSubmit={handleSubmit} >
            <label>Location ID :</label>
            <input className="field" type='text' name='locID' onChange={(e) => setLocNum(e.target.value)} required='required' />
            <label>Material ID :  </label>
            <input className="field" type='text' name='matID' onChange={(e) => setMatID(e.target.value)} /><br /><br />
            <button type="submit" name='submit'>Get Inventory</button><br /><br /><br />
        </form>

        {flag ?
            <table>
                <thead>
                    <tr>
                        <th>Location Number</th>
                        <th>Material Name</th>
                        <th>Available Quantity</th>
                        <th>Order Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    {inv}
                </tbody>
            </table> : ''}
        <br /><br />
        <div id="msg">{flag ? msg1 : msg}</div>
    </div>
}


