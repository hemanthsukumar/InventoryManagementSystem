import { useState } from "react"

export default function AddItems(props){

    const [item,setItem] = useState({locID : 0, matID : '', qty : 0});

    const [msg, setMsg] = useState('');

    const url = sessionStorage.getItem('url');

    const addItem = () => {

        console.log(item);
        let date = new Date().toJSON().slice(0, 10);

        if(item.locID === 0 || item.matID === '' || item.qty in [0,''] )
            setMsg('Enter Inputs')
        else
            fetch( url + '/addItem', {
                method : 'POST',
                headers : {'content-type' : 'application/JSON'},
                body : JSON.stringify({

                    availableQuantity : item.qty,
                    locationNumber : item.locID,
                    materialId : item.matID,
                    orderQuantity : 0,
                    resetQty : item.qty,
                    updateDate : date
                })
                
        }).then(data => data.json()).then(json => setMsg(json.msg))

    }

    return <div id="addItems">
        <table>
            <tr>
                <th>Location Number</th>
                <th>Material Name</th>
                <th>Quantity</th>
            </tr>
            <tr>
                <td><input className="field" required='required' type='number' name='locID' onChange={e => setItem({locID : e.target.value, matID : item.matID, qty : item.qty})}></input></td>
                <td><input className="field" required='required' type='text' name='matID' onChange={e => setItem({locID : item.locID, matID : e.target.value, qty : item.qty})}></input></td>
                <td><input className="field" required='required' type='number' name='qty' onChange={e => setItem({locID : item.locID, matID : item.matID, qty : e.target.value})}></input></td>
                
            </tr>
        </table>
        <button onClick={addItem}>Add</button>

        <div id="msg">{msg}</div>

    </div>

}