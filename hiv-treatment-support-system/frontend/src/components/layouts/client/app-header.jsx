import { Layout, Menu, theme, Avatar, Dropdown, Typography, Button, Space, Image } from 'antd';
import { UserOutlined, DownOutlined, LogoutOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';
import appLogo from '../../../assets/appLogo.png';

const { Header } = Layout;
const { Text } = Typography;

const AppHeader = ({ isAuthenticated = false, username = 'User' }) => {
    const {
        token: { colorBgContainer },
    } = theme.useToken();

    const items = [
        { key: '1', label: 'Trang chủ', path: '/' },
        { key: '2', label: 'Đặt lịch', path: '/booking' },
        // { key: '3', label: 'Hỏi đáp', path: '/' },
    ];
    const mapMenuItems = items.map(item => ({
        key: item.key,
        label: <Link to={item.path}>{item.label}</Link>,
    }));

    const activeMenu = items.find(item =>
        location.pathname === item.path ||
        location.pathname.startsWith(item.path + '/')
    )?.key;

    const menu = (
        <Menu
            items={[
                { key: 'profile', label: 'Profile' },
                { key: 'settings', label: 'Settings' },
            ]}
            onClick={({ key }) => {
                console.log(`Clicked on ${key}`);
            }}
        />
    );

    const handleLogout = () => {
        console.log('Logout clicked');
    };

    return (
        <Header
            style={{
                position: 'sticky',
                top: 0,
                zIndex: 1,
                width: '100%',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                background: colorBgContainer,
                padding: '0 24px',
            }}
        >
            <div
                className="demo-logo"
                style={{
                    width: 140,
                    height: 40,
                    marginRight: 24,
                    marginTop: 10,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    borderRadius: 8,

                }}
            >
                <Link to="/"><img
                    src={appLogo}
                    alt="logo"
                    style={{
                        maxWidth: "100%",
                        maxHeight: "100%",
                        objectFit: "contain",
                        display: "block",
                    }}
                /></Link>
            </div>

            <Menu
                theme="light"
                mode="horizontal"
                selectedKeys={activeMenu ? [activeMenu] : []}
                items={mapMenuItems}
                style={{
                    flex: 'none',
                    maxWidth: '800px',
                    width: '100%',
                    justifyContent: 'center'
                }}
            />

            {isAuthenticated ? (
                <Space align="center" size={8} style={{ cursor: 'default' }}>
                    <Dropdown overlay={menu} placement="bottomLeft" arrow>
                        <Space style={{ cursor: 'pointer' }} align="center">
                            <Avatar icon={<UserOutlined />} />
                            <Text style={{ color: '#fff', marginLeft: 4, marginRight: 4 }}>{username}</Text>
                            <DownOutlined style={{ color: '#fff' }} />
                        </Space>
                    </Dropdown>
                    <Button type="primary" icon={<LogoutOutlined />} onClick={handleLogout} danger>
                        Đăng xuất
                    </Button>
                </Space>
            ) : (
                <Space size="middle">
                    <Link to="/login">
                        <Button type="primary">Đăng nhập</Button>
                    </Link>
                    <Link to="/register">
                        <Button>Đăng kí</Button>
                    </Link>
                </Space>
            )}
        </Header>
    );
};

export default AppHeader;

