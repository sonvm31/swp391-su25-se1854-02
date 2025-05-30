import React, { useState } from 'react';
import { Form, Input, Select, DatePicker, TimePicker, Button, Typography, Col, Row, Layout, theme } from 'antd';
import AppHeader from '../../components/AppHeader';
import AppFooter from '../../components/AppFooter';
import { ArrowLeftOutlined } from '@ant-design/icons';
import moment from 'moment';


const { Link } = Typography;
const { Option } = Select;
const { Content } = Layout;
const dateFormat = 'DD/MM/YYYY';

const Booking = () => {
    const [form] = Form.useForm();
    const [availableTimes, setAvailableTimes] = useState(generateTimeSlots());

    const handleSubmit = (values) => {
        console.log('Submitted values:', values);
        // You can add code here to send the data to the server
    };
    const disabledDate = (current) => {
        // Disallow dates before today
        return current && current < moment().startOf('day');
    };
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    function generateTimeSlots() {
        const times = [];
        const startHour = 8;  // Customize start time based on your image
        const endHour = 16;   // Customize end time based on your image
        for (let hour = startHour; hour <= endHour; hour++) {
            times.push(moment(`${hour}:00`, 'HH:mm'));
            times.push(moment(`${hour}:30`, 'HH:mm'));
        }
        return times;
    }

    return (
        <Layout>
            <AppHeader />
            <Content style={{ padding: '0 20px' }}>
                <Row justify="center">
                    <Col span={16} style={{ background: 'white', borderRadius: '10px', margin: '20px', boxShadow: '0 4px 12px rgba(0,0,0,0.15)' }}>
                        <Link href="/"><ArrowLeftOutlined style={{ margin: '15px' }} /> Về trang chủ</Link>
                        <div style={{ maxWidth: 900, margin: '0 auto' }}>

                            <h1>Đặt lịch khám</h1>
                            <p>Vui lòng điền thông tin dưới đây để đặt lịch khám với bác sĩ chuyên khoa HIV</p>
                            <Form form={form} layout="vertical" onFinish={handleSubmit}>
                                <Row gutter={8}>
                                    <Col span={12}>
                                        <Form.Item
                                            name="name"
                                            label="Họ và tên"
                                            rules={[{ required: true, message: 'Vui lòng nhập họ và tên' }]}
                                        >
                                            <Input placeholder="Nguyễn Văn A" />
                                        </Form.Item>
                                    </Col>
                                    <Col span={12}>
                                        <Form.Item
                                            name="phone"
                                            label="Số điện thoại"
                                            rules={[{ required: true, message: 'Vui lòng nhập số điện thoại' }]}
                                        >
                                            <Input placeholder="0912345678" />
                                        </Form.Item>
                                    </Col>
                                </Row>


                                <Form.Item
                                    name="service"
                                    label="Loại dịch vụ"
                                    rules={[{ required: true, message: 'Vui lòng chọn loại dịch vụ' }]}
                                >
                                    <Select placeholder="Chọn loại dịch vụ">
                                        <Option value="service1">Dịch vụ 1</Option>
                                        <Option value="service2">Dịch vụ 2</Option>
                                    </Select>
                                </Form.Item>
                                <Form.Item
                                    name="doctor"
                                    label="Bác sĩ"
                                    rules={[{ required: true, message: 'Vui lòng chọn bác sĩ' }]}
                                >
                                    <Select placeholder="Chọn bác sĩ">
                                        <Option value="doctor1">Bác sĩ 1</Option>
                                        <Option value="doctor2">Bác sĩ 2</Option>
                                    </Select>
                                </Form.Item>
                                <Row gutter={8} >
                                    <Col span={12}>
                                        <Form.Item
                                            name="date"
                                            label="Ngày khám"
                                            rules={[{ required: true, message: 'Vui lòng chọn ngày khám' }]}
                                        >
                                            <DatePicker disabledDate={disabledDate} format={dateFormat} style={{ width: '100%' }} />
                                        </Form.Item>
                                    </Col>
                                    <Col span={12}>
                                        <Form.Item
                                            name="time"
                                            label="Giờ khám"
                                            rules={[{ required: true, message: 'Vui lòng chọn giờ khám' }]}
                                        >
                                            <Select placeholder="Chọn giờ khám">
                                                {availableTimes.map((time) => (
                                                    <Option key={time.format('HH:mm')} value={time.format('HH:mm')}>
                                                        {time.format('HH:mm')}
                                                    </Option>
                                                ))}
                                            </Select>
                                        </Form.Item>
                                    </Col>
                                </Row>


                                <Form.Item name="notes" label="Ghi chú (nếu có)">
                                    <Input.TextArea placeholder="Triệu chứng, yêu cầu đặc biệt..." />
                                </Form.Item>
                                <Form.Item style={{ display: 'flex', justifyContent: 'flex-end' }}>
                                    <Button type="primary" htmlType="submit">
                                        Xác nhận đặt lịch
                                    </Button>
                                </Form.Item>
                            </Form>

                        </div>
                    </Col>
                    <Col span={4}>
                        <div style={{ marginTop: 20 }}>
                            <h2>Thông tin hỗ trợ</h2>
                            <p><strong>Giờ làm việc:</strong> Thứ Hai - Thứ Sáu: 8:00 - 16:30</p>
                            <p><strong>Liên hệ hỗ trợ:</strong></p>
                            <p>Hotline: 1900 1234</p>
                            <p>Email: support@hivcarecenter.vn</p>
                            <h3>Lưu ý</h3>
                            <ul>
                                <li>Vui lòng đến trước giờ hẹn 15 phút</li>
                                <li>Mang theo giấy tờ tùy thân và thẻ BHYT (nếu có)</li>
                                <li>Cập nhật thông tin sức khỏe gần nhất</li>
                            </ul>
                        </div>
                    </Col>
                </Row>



            </Content>
            <AppFooter />
        </Layout>

    );
};

export default Booking;
