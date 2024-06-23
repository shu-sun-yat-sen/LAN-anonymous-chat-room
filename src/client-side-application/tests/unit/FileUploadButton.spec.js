import { shallowMount } from '@vue/test-utils';
// import FileUploadButton from 'components/FileUploadButton.vue';
import FileUploadButton from '@/components/inputWindowComponent/FileUploadButton.vue';

describe('FileUploadButton.vue', () => {
  let wrapper;
  beforeEach(() => {
    wrapper = shallowMount(FileUploadButton);
  });

  it('renders a button', () => {
    const button = wrapper.find('button');
    expect(button.exists()).toBe(true);
  });

  it('calls clickUpload when button is clicked', async () => {
    const clickUploadSpy = jest.spyOn(wrapper.vm, 'clickUpload');
    await wrapper.find('button').trigger('click');
    expect(clickUploadSpy).toHaveBeenCalled();
  });

  it('clickUpload triggers input click', async () => {
    const input = wrapper.find('input[type="file"]');
    const inputClickSpy = jest.spyOn(input.element, 'click');
    await wrapper.vm.clickUpload();
    expect(inputClickSpy).toHaveBeenCalled();
  });

  it('emits upload-file event when a file is selected', async () => {
    const file = new Blob(['file content'], { type: 'text/plain' });
    // const input = wrapper.find('input[type="file"]');
    const event = { target: { files: [file] } };
    wrapper.vm.uploadFile(event); // 直接调用组件的方法
    await wrapper.vm.$nextTick(); // 等待 Vue 完成响应
    expect(wrapper.emitted('upload-file')).toBeTruthy();
    expect(wrapper.emitted('upload-file')[0]).toEqual([file]);
  });
});
