import { useState } from "react"

export default function EditInventory(props){

    const [locNum, setLocNum]  = useState('');
    const [matID, setMatID]  = useState('');
    const [inv, setInv] = useState();
    
    const [msg, setMsg] = useState('');  

    const [flag,setFlag] = useState(false);
    const [msg1, setMsg1] = useState('');

    const url = sessionStorage.getItem('url');

    const  setInvenotory = (data) => {
        
        if(matID === ''){
            setInv(data.map((data) => <tr>
                <td>{data.locationNumber}</td>
                <td>{data.materialNumber}</td>
                <td>{data.resetQty}</td>
                <td>{data.orderQuantity}</td>
                <td>{data.availableQuantity}</td>
                <td>{data.updateDate.slice(0,10)}</td>
                <td><input className="field" type='number' id={'qnt'+data.materialNumber} onChange={(e)=>data.availableQuantity = e.target.value} placeholder={data.availableQuantity}/></td>
                <td><button onClick={() => handleUpdate(data.locationNumber,data.materialNumber,'qnt'+data.materialNumber)}>update</button></td>
            </tr>))
        }else
        setInv(
            <tr>
                <td>{data.locationNumber}</td>
                <td>{data.materialNumber}</td>
                <td>{data.resetQty}</td>
                <td>{data.orderQuantity}</td>
                <td>{data.availableQuantity}</td>
                <td>{data.updateDate.slice(0,10)}</td>
                <td><input className="field" type='number' id={'qnt'+data.materialNumber} onChange={(e)=>data.availableQuantity = e.target.value} placeholder={data.availableQuantity}/></td>
                <td><button onClick={() => handleUpdate(data.locationNumber,data.materialNumber,'qnt'+data.materialNumber)}>update</button></td>
            </tr>
        )
    }

    const getInv = () => {

        if(matID === '')
            fetch(url + '/getInventorys/'+locNum)
            .then(data => {
                if(data.ok) {
                    setMsg('');
                    data.json().then(data => setInvenotory(data));
                    
                    }
                else{
                    setFlag(false);
                    setInv('');
                    setMsg("No Data Found");
            }})
        else    
            fetch(url + '/getInventory/'+locNum+'/'+matID)
            .then(data => {
                if(data.ok) {
                    setMsg('');
                    data.json().then(data => setInvenotory(data));
                    }
                else{
                    console.log(124)
                    setFlag(false);
                    setInv('');
                    setMsg("No Data Found");
            }})
    }

    const handleUpdate = (l,m,id) => {
        var q = document.getElementById(id).value;
        fetch(url + '/updateInventory/'+l+'/'+m+'/'+q,{
            method:'post'
        })
        .then(data => data.json()
        .then(data => {setMsg1(  data.msg +' *** ' + m + ' *** ');
                        getInv();
        }));
    }


    const handleSubmit = () => {
        
        setFlag(true);
        setMsg1('');
        
        getInv();
        
        

    }

    return <div>
        
        <label>Location ID :</label>
        <input className="field" type='text' name='locID' onChange={(e) => setLocNum(e.target.value)} required='required'/>
        <label>Material Name :  </label>
        <input className="field" type='text' name='matID' onChange={(e) => setMatID(e.target.value)}/><br/><br/>
        <button className="field" onClick={()=>handleSubmit()} name = 'submit'>Get Inventory</button><br/><br/><br/>

        {flag ? 
        <table>
            <thead>
            <tr>
                <th>Location Number</th>
                <th>Material Name</th>
                <th>Rest Quantity</th>
                <th>Order Quantity</th>
                <th>Available Quantity</th>
                <th>Update Date</th>
                <th>Update Rest Quantity</th>
            </tr>
            </thead>
            <tbody>
                {inv}
            </tbody>
        </table> : ''}
        <br/><br/>
        <div id="msg">{flag ? msg1:msg}</div>

    </div>
}


