import { Button, Space, Table, Tag } from 'antd';


const columns = [
    {
        title: 'Tên đăng nhập',
        dataIndex: 'username',
        key: 'username',
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Trạng thái',
        key: 'status',
        dataIndex: 'status',
        render: (_, { status }) => {

            let color = status === 'active' ? 'green' : 'volcano';
            let text = status === 'active' ? 'Đang hoạt động' : 'Bị khóa';

            return (
                <Tag color={color} key={status}>
                    {text}
                </Tag>
            );

        },
    },
    {
        title: 'Action',
        key: 'action',
        render: (_, record) => (
            <Space size="middle">
                <a>Invite {record.name}</a>
                <a>Delete</a>
            </Space>
        ),
    },
];


const data = [
    {
        key: '1',
        username: 'manager1',
        email: 'manager1@gmail.com',
        status: 'active'
    },
    {
        key: '2',
        username: 'manager2',
        email: 'manager2@gmail.com',
        status: 'active'
    },
    {
        key: '3',
        username: 'manager3',
        email: 'manager3@gmail.com',
        status: 'active'
    },
];
const AccountStaff = () => {
    return (
        <>
            <div style={{ display: 'flex', justifyContent: 'space-between', padding: '15px' }}>
                <h2>Tài khoản nhân viên</h2>
                <Button type='primary'>Tạo mới</Button>
            </div>
            <Table columns={columns} dataSource={data} />
        </>
    )
}
export default AccountStaff;