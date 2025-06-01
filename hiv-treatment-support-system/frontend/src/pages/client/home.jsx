
import { Layout, theme } from 'antd';
import AppHeader from '../../components/layouts/client/app-header';
import AppFooter from '../../components/layouts/client/app-footer';
const { Content } = Layout;

const Home = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout>
            <AppHeader />
            <Content style={{ padding: '15px' }}>
                <div
                    style={{
                        background: colorBgContainer,
                        minHeight: 1080,
                        padding: 24,
                        borderRadius: borderRadiusLG,
                    }}
                >

                </div>
            </Content>
            <AppFooter />
        </Layout>
    );
};
export default Home;