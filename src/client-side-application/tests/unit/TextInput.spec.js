import { shallowMount } from '@vue/test-utils';
import TextInput from '@/components/inputWindowComponent/TextInput.vue'; // 请根据实际路径调整导入路径

jest.useFakeTimers();

describe('TextInput.vue', () => {
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(TextInput, {
      props: { emojiMessage: '' }
    });
  });

  it('renders a textarea and a button', () => {
    expect(wrapper.find('textarea').exists()).toBe(true);
    expect(wrapper.find('button').exists()).toBe(true);
  });

  it('updates the text data when textarea value changes', async () => {
    const textarea = wrapper.find('textarea');
    await textarea.setValue('Hello');
    expect(wrapper.vm.text).toBe('Hello');
  });

  it('sends text when Enter is pressed', async () => {
    const textarea = wrapper.find('textarea');
    await textarea.setValue('Hello');
    await textarea.trigger('keydown', { key: 'Enter' });
    jest.runAllTimers();
    expect(wrapper.emitted('send-text')).toBeTruthy();
    expect(wrapper.emitted('send-text')[0]).toEqual(['Hello']);
    expect(wrapper.vm.text).toBe('');
  });

  it('inserts a newline when Ctrl+Enter is pressed', async () => {
    const textarea = wrapper.find('textarea');
    await textarea.setValue('Hello');
    await textarea.trigger('keydown', { key: 'Enter', ctrlKey: true });
    jest.runAllTimers();
    expect(wrapper.vm.text).toBe('Hello\n');
  });

  it('sends text when the send button is clicked', async () => {
    const textarea = wrapper.find('textarea');
    await textarea.setValue('Hello');
    const button = wrapper.find('button');
    await button.trigger('click');
    expect(wrapper.emitted('send-text')).toBeTruthy();
    expect(wrapper.emitted('send-text')[0]).toEqual(['Hello']);
    expect(wrapper.vm.text).toBe('');
  });

  it('appends emojiMessage to text and clears emojiMessage when prop changes', async () => {
    await wrapper.setProps({ emojiMessage: '😀' });
    expect(wrapper.vm.text).toBe('😀');
    expect(wrapper.emitted('clearEmojiMessage')).toBeTruthy();
  });

  it('does not send empty text', async () => {
    const button = wrapper.find('button');
    await button.trigger('click');
    expect(wrapper.emitted('send-text')).toBeFalsy();
  });

  it('prevents sending text rapidly', async () => {
    const textarea = wrapper.find('textarea');
    await textarea.setValue('Hello');
    await textarea.trigger('keydown', { key: 'Enter' });
    await textarea.trigger('keydown', { key: 'Enter' });
    jest.runAllTimers();
    expect(wrapper.emitted('send-text')).toHaveLength(1);
  });
});
