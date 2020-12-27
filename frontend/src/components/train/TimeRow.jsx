export default function TimeRow({schedule}) {
    const getDelayClass = (delay) => {
       if (delay >= 60)
           return 'critical';
       else if (delay >= 20)
           return 'moderate';
       else if (delay >= 5)
           return 'minor';
       else
           return 'normal';
    };

    const formatDate = (date) => {
        const d = new Date(date);
        const h = d.getHours() < 10 ? '0' + d.getHours() : d.getHours();
        const m = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes();
        return h + ':' + m;
    };

    const makeTimeCell = (info) => {
        const className = getDelayClass(Math.max(info.arrival_delay, info.departure_delay));
        return (
            <td key={info.station_name} className={className}>
                <table>
                    <tbody>
                        {info.arrival_time &&
                            <tr><td>→</td><td className='time'>
                                {formatDate(info.arrival_time)}
                                ({info.arrival_delay} min)
                            </td><td></td></tr>}

                        {info.departure_time &&
                            <tr><td></td><td className='time'>
                                {formatDate(info.departure_time)}
                                ({info.departure_delay} min)
                                </td><td>→</td></tr>}
                    </tbody>
                </table>
            </td>
        );
    };

    return (
        <tr>
            {schedule.info.map(makeTimeCell)}
        </tr>
    );
}
