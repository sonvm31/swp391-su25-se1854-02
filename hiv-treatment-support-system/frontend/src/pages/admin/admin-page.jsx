import '../../styles/global.css'
import { Layout, theme } from 'antd';
import AdminHeader from '../../components/layouts/admin/admin-header';
import AdminSidebar from '../../components/layouts/admin/admin-sidebar';
import { Outlet } from 'react-router-dom';
const { Content } = Layout;

const Admin = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout>
            <AdminHeader />
            <Layout>
                <AdminSidebar />
                <Layout style={{ padding: '15px' }}>
                    <Content
                        style={{
                            padding: 15,
                            minHeight: 1080,
                            background: colorBgContainer,
                            borderRadius: borderRadiusLG,
                        }}
                    >
                        <Outlet />
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    );
};
export default Admin;