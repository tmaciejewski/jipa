import {useState} from 'react';
import {Link} from 'react-router-dom';

export default function TrainElement({train}) {
    const [activeStation, setActiveStation] = useState(1);
    const firstIntermediateStation = 1;
    const lastIntermediateStation = train.stations.length - 2;

    return (
        <tr>
            <td><Link to={`/train/${train.train_name}`}>{train.train_name}</Link></td>
            <td>{train.stations[0]}</td>
            <td>
                <table>
                    <tbody>
                        <tr>
                            <td className='arrow-button'>
                                {activeStation > firstIntermediateStation && <button onClick={() => setActiveStation(activeStation - 1)}>&#8592;</button>}
                            </td>
                            <td className='intermediate-station'>{train.stations[activeStation]}</td>
                            <td className='arrow-button'>
                                {activeStation < lastIntermediateStation && <button onClick={() => setActiveStation(activeStation + 1)}>&#8594;</button>}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
            <td>{train.stations[train.stations.length - 1]}</td>
        </tr>
    );
}
