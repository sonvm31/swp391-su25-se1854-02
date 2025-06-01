import { useEffect, useState } from 'react';
import { Button, Input, Modal, notification, Select, Space, Table, Tag } from 'antd';
import { createAccountAPI, fetchAccountsAPI } from '../../services/api.service';
import { DeleteOutlined, EditOutlined } from '@ant-design/icons';

const AccountManagers = () => {

    const [data, setData] = useState([
        {
            username: 'hello',
            email: 'hello@gmail.com',
            role: 'MANAGER',
            status: 'active'
        },
    ])
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [email, setEmail] = useState("")
    const [role, setRole] = useState("MANAGER")
    const [isOpenModal, setIsOpenModal] = useState(false)

    useEffect(() => {
        loadAccounts()
    }, [])

    const loadAccounts = async () => {
        const response = await fetchAccountsAPI(role)
        setData(response.data)
    }

    const handleCreate = async () => {
        const response = await createAccountAPI(username, password, email, role)
        if (response.data) {
            notification.success({
                message: 'Đăng kí thành công'
            })
        }
        setIsOpenModal(false)
        await loadAccounts()
    }

    const test = () => {
        alert("clicked")
    }

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
            render: () => (
                <Space size="large">
                    <EditOutlined onClick={test} style={{ color: 'orange' }} />
                    <DeleteOutlined onClick={test} style={{ color: 'red' }} />
                </Space>
            ),
        },
    ];

    return (
        <>
            <div style={{ display: 'flex', justifyContent: 'space-between', padding: '15px' }}>
                <h2>Tài khoản quản lí</h2>
                <Button onClick={() => setIsOpenModal(true)} type='primary'>Tạo mới</Button>
            </div>
            <Table columns={columns} dataSource={data} rowKey={"id"} />
            <Modal
                title="Tạo tài khoản cho quản lí"
                closable={{ 'aria-label': 'Custom Close Button' }}
                open={isOpenModal}
                onOk={() => setIsOpenModal(false)}
                onCancel={() => setIsOpenModal(false)}
                okText={"Tạo"}
                cancelText={"Hủy"}
            >
                <div style={{ display: 'flex', gap: '15px', flexDirection: 'column' }}>
                    <div>
                        <span>Tên đăng nhập</span>
                        <Input value={username} onChange={(event) => { setUsername(event.target.value) }} />
                    </div>
                    <div>
                        <span>Email</span>
                        <Input value={email} onChange={(event) => { setEmail(event.target.value) }} />
                    </div>
                    <div>
                        <span>Mật khẩu</span>
                        <Input.Password value={password} onChange={(event) => { setPassword(event.target.value) }} />
                    </div>
                    <div>
                        <span>Vai trò</span>
                        <Input disabled value={role} />
                    </div>
                </div>
            </Modal>
        </>
    )
}
export default AccountManagers;