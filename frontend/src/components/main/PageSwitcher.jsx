export default function PageSwitcher({page, pageCount, onPageChanged}) {
    return (
        <table id='menu'>
            <tbody>
                <tr>
                    <td className='arrow-button'>
                        {page > 1 && <button onClick={() => onPageChanged(page - 1)}>Poprzednia strona</button>}
                    </td>
                    <td>
                        <p>Strona {page} z {pageCount}</p>
                    </td>
                    <td className='arrow-button'>
                        {page < pageCount && <button onClick={() => onPageChanged(page + 1)}>Następna strona</button>}
                    </td>
                </tr>
            </tbody>
        </table>
    );
}
