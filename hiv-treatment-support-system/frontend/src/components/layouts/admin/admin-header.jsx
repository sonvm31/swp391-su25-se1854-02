import { Layout, Button, Avatar, Typography } from "antd";
import { UserOutlined, LogoutOutlined } from "@ant-design/icons";

const { Header } = Layout
const { Text } = Typography


const handleLogout = () => {
    console.log('Logout clicked');
};


const AdminHeader = () => {
    return (
        <Header style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <div>
                <Avatar icon={<UserOutlined />} />
                <Text style={{ color: '#fff', marginLeft: 4, marginRight: 4 }}>Admin</Text>
            </div>
            <Button type="primary" icon={<LogoutOutlined />} onClick={handleLogout} danger>
                Đăng xuất
            </Button>
        </Header>
    )
}

export default AdminHeader